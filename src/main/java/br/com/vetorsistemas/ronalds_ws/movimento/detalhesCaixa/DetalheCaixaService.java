package br.com.vetorsistemas.ronalds_ws.movimento.detalhesCaixa;

import br.com.vetorsistemas.ronalds_ws.movimento.detalhesCaixa.dto.DetalheCaixaDto;
import br.com.vetorsistemas.ronalds_ws.movimento.detalhesCaixa.mapper.DetalheCaixaMapper;
import br.com.vetorsistemas.ronalds_ws.shared.AppException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class DetalheCaixaService {
    private final DetalheCaixaRepository repository;
    private final DetalheCaixaMapper mapper;

    public DetalheCaixaService(DetalheCaixaRepository repository, DetalheCaixaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public Page<DetalheCaixaDto> list(String planoContas,
                                      String numeroDocumento,
                                      String historico,
                                      LocalDateTime dataMovimentoDe,
                                      LocalDateTime dataMovimentoAte,
                                      Double valorMovimentoDe,
                                      Double valorMovimentoAte,
                                      Integer pagina,
                                      Integer tamanhoPagina) {
        if (pagina == null || pagina < 0) pagina = 0;
        if (tamanhoPagina == null || tamanhoPagina <= 0) tamanhoPagina = 10;
        var specs = (org.springframework.data.jpa.domain.Specification<DetalheCaixa>) (root, query, cb) -> cb.conjunction();
        String doc = normalizeLike(numeroDocumento);
        String hist = normalizeLike(historico);
        if (!isBlank(planoContas)) specs = specs.and((r, q, cb) -> cb.equal(r.get("planoContas"), planoContas));
        if (doc != null) specs = specs.and((r, q, cb) -> cb.like(cb.upper(r.get("numeroDocumento")), "%" + doc + "%"));
        if (hist != null) specs = specs.and((r, q, cb) -> cb.like(cb.upper(r.get("historico")), "%" + hist + "%"));
        if (dataMovimentoDe != null) specs = specs.and((r, q, cb) -> cb.greaterThanOrEqualTo(r.get("dataMovimento"), dataMovimentoDe));
        if (dataMovimentoAte != null) specs = specs.and((r, q, cb) -> cb.lessThanOrEqualTo(r.get("dataMovimento"), dataMovimentoAte));
        if (valorMovimentoDe != null) specs = specs.and((r, q, cb) -> cb.greaterThanOrEqualTo(r.get("valorMovimento"), valorMovimentoDe));
        if (valorMovimentoAte != null) specs = specs.and((r, q, cb) -> cb.lessThanOrEqualTo(r.get("valorMovimento"), valorMovimentoAte));
        Pageable page = PageRequest.of(pagina, tamanhoPagina, Sort.by("dataMovimento").descending());
        return repository.findAll(specs, page).map(mapper::toDTO);
    }

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    private static String normalizeLike(String s) {
        if (isBlank(s)) return null;
        return s.trim().toUpperCase();
    }

    public DetalheCaixaDto findById(Integer id) {
        DetalheCaixa entity = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Detalhe do caixa não encontrado"));
        return mapper.toDTO(entity);
    }

    @Transactional
    public DetalheCaixaDto create(DetalheCaixaDto dto) {
        DetalheCaixa entity = mapper.fromDTO(dto);
        entity.setId(null);
        applyBusinessRules(entity);
        return mapper.toDTO(repository.save(entity));
    }

    @Transactional
    public DetalheCaixaDto update(Integer id, DetalheCaixaDto dto) {
        DetalheCaixa entity = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Detalhe do caixa não encontrado"));
        mapper.updateEntityFromDTO(dto, entity);
        applyBusinessRules(entity);
        return mapper.toDTO(repository.save(entity));
    }

    @Transactional
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND, "Detalhe do caixa não encontrado");
        }
        repository.deleteById(id);
    }

    private void applyBusinessRules(DetalheCaixa entity) {
        if (entity == null) return;
        entity.setCodigoCaixa(0);
        entity.setStatus("N");
        normalizeValor(entity);
    }

    private void normalizeValor(DetalheCaixa entity) {
        Double valor = entity.getValorMovimento();
        if (valor == null) return;
        String debCre = entity.getDebitoCredito();
        if (debCre == null) return;
        String normalized = debCre.trim().toUpperCase();
        entity.setDebitoCredito(normalized);
        if ("D".equals(normalized)) {
            entity.setValorMovimento(-Math.abs(valor));
        } else if ("C".equals(normalized)) {
            entity.setValorMovimento(Math.abs(valor));
        }
    }
}
