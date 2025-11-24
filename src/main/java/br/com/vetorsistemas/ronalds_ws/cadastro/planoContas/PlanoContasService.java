package br.com.vetorsistemas.ronalds_ws.cadastro.planoContas;

import br.com.vetorsistemas.ronalds_ws.cadastro.planoContas.dto.PlanoContasDto;
import br.com.vetorsistemas.ronalds_ws.cadastro.planoContas.mapper.PlanoContasMapper;
import br.com.vetorsistemas.ronalds_ws.shared.AppException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlanoContasService {
    private final PlanoContasRepository repository;
    private final PlanoContasMapper mapper;

    public PlanoContasService(PlanoContasRepository repository, PlanoContasMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public Page<PlanoContasDto> list(String descricao, Integer pagina, Integer tamanhoPagina) {
        if (pagina == null || pagina < 0) pagina = 0;

        if (tamanhoPagina == null || tamanhoPagina <= 0) tamanhoPagina = 10;

        var specs = (org.springframework.data.jpa.domain.Specification<PlanoContas>) (root, query, cb) -> cb.conjunction();
        String desc = normalizeLike(descricao);

        if (desc != null) specs = specs.and((r, q, cb) -> cb.like(cb.upper(r.get("descricao")), "%" + desc + "%"));
        Pageable page = PageRequest.of(pagina, tamanhoPagina, Sort.by("descricao").ascending());
        return repository.findAll(specs, page).map(mapper::toDTO);
    }

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    private static String normalizeLike(String s) {
        if (isBlank(s)) return null;
        return s.trim().toUpperCase();
    }

    public PlanoContasDto findById(String id) {
        PlanoContas e = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Plano de contas não encontrado"));
        return mapper.toDTO(e);
    }

    @Transactional
    public PlanoContasDto create(PlanoContasDto dto) {
        PlanoContas e = mapper.fromDTO(dto);
        return mapper.toDTO(repository.save(e));
    }

    @Transactional
    public PlanoContasDto update(String id, PlanoContasDto dto) {
        PlanoContas e = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Plano de contas não encontrado"));
        mapper.updateEntityFromDTO(dto, e);
        return mapper.toDTO(repository.save(e));
    }

    @Transactional
    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND, "Plano de contas não encontrado");
        }
        repository.deleteById(id);
    }
}
