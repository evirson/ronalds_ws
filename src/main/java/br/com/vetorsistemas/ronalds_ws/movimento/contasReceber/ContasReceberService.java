package br.com.vetorsistemas.ronalds_ws.movimento.contasReceber;

import br.com.vetorsistemas.ronalds_ws.movimento.contasReceber.dto.ContasReceberDto;
import br.com.vetorsistemas.ronalds_ws.movimento.contasReceber.mapper.ContasReceberMapper;
import br.com.vetorsistemas.ronalds_ws.shared.AppException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContasReceberService {
    private final ContasReceberRepository repository;
    private final ContasReceberMapper mapper;

    public ContasReceberService(ContasReceberRepository repository, ContasReceberMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public Page<ContasReceberDto> list(Integer idCliente,
                                       String documento,
                                       String previs,
                                       java.time.LocalDateTime dataMovimentoDe,
                                       java.time.LocalDateTime dataMovimentoAte,
                                       Integer pagina, Integer tamanhoPagina) {
        if (pagina == null || pagina < 0) pagina = 0;
        if (tamanhoPagina == null || tamanhoPagina <= 0) tamanhoPagina = 10;
        var specs = (org.springframework.data.jpa.domain.Specification<ContasReceber>) (root, query, cb) -> cb.conjunction();
        String doc = normalizeLike(documento);
        String prev = normalizeLike(previs);
        if (idCliente != null) specs = specs.and((r,q,cb) -> cb.equal(r.get("idCliente"), idCliente));
        if (doc != null) specs = specs.and((r,q,cb) -> cb.like(cb.upper(r.get("documento")), "%"+doc+"%"));
        if (prev != null) specs = specs.and((r,q,cb) -> cb.like(cb.upper(r.get("previs")), "%"+prev+"%"));
        if (dataMovimentoDe != null) specs = specs.and((r,q,cb) -> cb.greaterThanOrEqualTo(r.get("dataMovimento"), dataMovimentoDe));
        if (dataMovimentoAte != null) specs = specs.and((r,q,cb) -> cb.lessThanOrEqualTo(r.get("dataMovimento"), dataMovimentoAte));
        Pageable page = PageRequest.of(pagina, tamanhoPagina, Sort.by("dataMovimento").descending());
        return repository.findAll(specs, page).map(mapper::toDTO);
    }

    private static boolean isBlank(String s){return s==null||s.trim().isEmpty();}
    private static String normalizeLike(String s){if(isBlank(s))return null;return s.trim().toUpperCase();}

    public ContasReceberDto findById(Integer id) {
        ContasReceber e = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Contas a receber não encontrada"));
        return mapper.toDTO(e);
    }

    @Transactional
    public ContasReceberDto create(ContasReceberDto dto) {
        ContasReceber e = mapper.fromDTO(dto);
        e.setId(null);
        return mapper.toDTO(repository.save(e));
    }

    @Transactional
    public ContasReceberDto update(Integer id, ContasReceberDto dto) {
        ContasReceber e = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Contas a receber não encontrada"));
        mapper.updateEntityFromDTO(dto, e);
        return mapper.toDTO(repository.save(e));
    }

    @Transactional
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND, "Contas a receber não encontrada");
        }
        repository.deleteById(id);
    }
}
