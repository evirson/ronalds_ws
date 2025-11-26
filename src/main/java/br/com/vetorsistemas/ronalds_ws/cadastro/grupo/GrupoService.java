package br.com.vetorsistemas.ronalds_ws.cadastro.grupo;

import br.com.vetorsistemas.ronalds_ws.cadastro.grupo.dto.GrupoDto;
import br.com.vetorsistemas.ronalds_ws.cadastro.grupo.mapper.GrupoMapper;
import br.com.vetorsistemas.ronalds_ws.shared.AppException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GrupoService {
    private final GrupoRepository repository;
    private final GrupoMapper mapper;

    public GrupoService(GrupoRepository repository, GrupoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public Page<GrupoDto> list(String nomeGrupo, Integer pagina, Integer tamanhoPagina) {
        if (pagina == null || pagina < 0) pagina = 0;

        if (tamanhoPagina == null || tamanhoPagina <= 0) tamanhoPagina = 10;

        var specs = (org.springframework.data.jpa.domain.Specification<Grupo>) (root, query, cb) -> cb.conjunction();
        String nome = normalizeLike(nomeGrupo);

        if (nome != null) specs = specs.and((r, q, cb) -> cb.like(cb.upper(r.get("nomeGrupo")), "%" + nome + "%"));
        Pageable page = PageRequest.of(pagina, tamanhoPagina, Sort.by("nomeGrupo").ascending());

        return repository.findAll(specs, page).map(mapper::toDTO);
    }

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    private static String normalizeLike(String s) {
        if (isBlank(s)) return null;
        return s.trim().toUpperCase();
    }

    public GrupoDto findById(Integer id) {
        Grupo e = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Grupo não encontrado"));
        return mapper.toDTO(e);
    }

    @Transactional
    public GrupoDto create(GrupoDto dto) {
        Grupo e = mapper.fromDTO(dto);
        e.setId(null);
        return mapper.toDTO(repository.save(e));
    }

    @Transactional
    public GrupoDto update(Integer id, GrupoDto dto) {
        Grupo e = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Grupo não encontrado"));
        mapper.updateEntityFromDTO(dto, e);
        return mapper.toDTO(repository.save(e));
    }

    @Transactional
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND, "Grupo não encontrado");
        }
        repository.deleteById(id);
    }
}
