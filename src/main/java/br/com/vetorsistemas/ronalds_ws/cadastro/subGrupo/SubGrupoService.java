package br.com.vetorsistemas.ronalds_ws.cadastro.subGrupo;

import br.com.vetorsistemas.ronalds_ws.shared.AppException;
import br.com.vetorsistemas.ronalds_ws.cadastro.subGrupo.dto.SubGrupoDto;
import br.com.vetorsistemas.ronalds_ws.cadastro.subGrupo.mapper.SubGrupoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubGrupoService {
    private final SubGrupoRepository repository;
    private final SubGrupoMapper mapper;

    public SubGrupoService(SubGrupoRepository repository, SubGrupoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public Page<SubGrupoDto> list(String nomeSubGrupo, Integer pagina, Integer tamanhoPagina) {
        if (pagina == null || pagina < 0) pagina = 0;
        if (tamanhoPagina == null || tamanhoPagina <= 0) tamanhoPagina = 10;
        var specs = (org.springframework.data.jpa.domain.Specification<SubGrupo>) (root, query, cb) -> cb.conjunction();
        String nome = normalizeLike(nomeSubGrupo);
        if (nome != null) specs = specs.and((r,q,cb) -> cb.like(cb.upper(r.get("nomeSubGrupo")), "%"+nome+"%"));
        Pageable page = PageRequest.of(pagina, tamanhoPagina, Sort.by("nomeSubGrupo").ascending());
        return repository.findAll(specs, page).map(mapper::toDTO);
    }

    private static boolean isBlank(String s){return s==null||s.trim().isEmpty();}
    private static String normalizeLike(String s){if(isBlank(s))return null;return s.trim().toUpperCase();}

    public SubGrupoDto findById(Integer id) {
        SubGrupo e = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Subgrupo não encontrado"));
        return mapper.toDTO(e);
    }

    @Transactional
    public SubGrupoDto create(SubGrupoDto dto) {
        SubGrupo e = mapper.fromDTO(dto);
        e.setId(null);
        return mapper.toDTO(repository.save(e));
    }

    @Transactional
    public SubGrupoDto update(Integer id, SubGrupoDto dto) {
        SubGrupo e = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Subgrupo não encontrado"));
        mapper.updateEntityFromDTO(dto, e);
        return mapper.toDTO(repository.save(e));
    }

    @Transactional
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND, "Subgrupo não encontrado");
        }
        repository.deleteById(id);
    }
}
