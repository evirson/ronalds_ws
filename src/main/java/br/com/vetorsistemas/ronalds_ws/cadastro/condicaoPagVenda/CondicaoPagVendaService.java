package br.com.vetorsistemas.ronalds_ws.cadastro.condicaoPagVenda;

import br.com.vetorsistemas.ronalds_ws.cadastro.condicaoPagVenda.dto.CondicaoPagVendaDto;
import br.com.vetorsistemas.ronalds_ws.cadastro.condicaoPagVenda.mapper.CondicaoPagVendaMapper;
import br.com.vetorsistemas.ronalds_ws.shared.AppException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CondicaoPagVendaService {
    private final CondicaoPagVendaRepository repository;
    private final CondicaoPagVendaMapper mapper;

    public CondicaoPagVendaService(CondicaoPagVendaRepository repository, CondicaoPagVendaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public Page<CondicaoPagVendaDto> list(String descricao,
                                          Integer quantidadeParcelas,
                                          Integer pagina,
                                          Integer tamanhoPagina) {
        if (pagina == null || pagina < 0) pagina = 0;
        if (tamanhoPagina == null || tamanhoPagina <= 0) tamanhoPagina = 10;
        var specs = (org.springframework.data.jpa.domain.Specification<CondicaoPagVenda>) (root, query, cb) -> cb.conjunction();
        String desc = normalizeLike(descricao);
        if (desc != null) specs = specs.and((r,q,cb) -> cb.like(cb.upper(r.get("descricao")), "%"+desc+"%"));
        if (quantidadeParcelas != null) specs = specs.and((r,q,cb) -> cb.equal(r.get("quantidadeParcelas"), quantidadeParcelas));
        Pageable page = PageRequest.of(pagina, tamanhoPagina, Sort.by("descricao").ascending());
        return repository.findAll(specs, page).map(mapper::toDTO);
    }

    private static boolean isBlank(String s){return s==null||s.trim().isEmpty();}
    private static String normalizeLike(String s){if(isBlank(s))return null;return s.trim().toUpperCase();}

    public CondicaoPagVendaDto findById(Integer id) {
        CondicaoPagVenda e = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Condição Pag/Venda não encontrada"));
        return mapper.toDTO(e);
    }

    @Transactional
    public CondicaoPagVendaDto create(CondicaoPagVendaDto dto) {
        CondicaoPagVenda e = mapper.fromDTO(dto);
        e.setId(null);
        return mapper.toDTO(repository.save(e));
    }

    @Transactional
    public CondicaoPagVendaDto update(Integer id, CondicaoPagVendaDto dto) {
        CondicaoPagVenda e = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Condição Pag/Venda não encontrada"));
        mapper.updateEntityFromDTO(dto, e);
        return mapper.toDTO(repository.save(e));
    }

    @Transactional
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND, "Condição Pag/Venda não encontrada");
        }
        repository.deleteById(id);
    }
}
