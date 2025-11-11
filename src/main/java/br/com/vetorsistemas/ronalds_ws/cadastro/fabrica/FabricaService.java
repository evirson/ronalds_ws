package br.com.vetorsistemas.ronalds_ws.cadastro.fabrica;

import br.com.vetorsistemas.ronalds_ws.cadastro.fabrica.dto.FabricaDto;
import br.com.vetorsistemas.ronalds_ws.cadastro.fabrica.mapper.FabricaMapper;
import br.com.vetorsistemas.ronalds_ws.shared.AppException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FabricaService {
    private final FabricaRepository repository;
    private final FabricaMapper mapper;

    public FabricaService(FabricaRepository repository, FabricaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public Page<FabricaDto> list(String nomeFabricante, Integer pagina, Integer tamanhoPagina) {
        if (pagina == null || pagina < 0) pagina = 0;
        if (tamanhoPagina == null || tamanhoPagina <= 0) tamanhoPagina = 10;

        var specs = (org.springframework.data.jpa.domain.Specification<Fabrica>) (root, query, cb) -> cb.conjunction();
        String nome = normalizeLike(nomeFabricante);
        if (nome != null) specs = specs.and((r,q,cb) -> cb.like(cb.upper(r.get("nomeFabricante")), "%"+nome+"%"));

        Pageable page = PageRequest.of(pagina, tamanhoPagina, Sort.by("nomeFabricante").ascending());
        return repository.findAll(specs, page).map(mapper::toDTO);
    }

    private static boolean isBlank(String s){return s==null||s.trim().isEmpty();}
    private static String normalizeLike(String s){if(isBlank(s))return null;return s.trim().toUpperCase();}

    public FabricaDto findById(Integer id) {
        Fabrica e = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Fábrica não encontrada"));
        return mapper.toDTO(e);
    }

    @Transactional
    public FabricaDto create(FabricaDto dto) {
        Fabrica e = mapper.fromDTO(dto);
        e.setId(null);
        return mapper.toDTO(repository.save(e));
    }

    @Transactional
    public FabricaDto update(Integer id, FabricaDto dto) {
        Fabrica e = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Fábrica não encontrada"));
        mapper.updateEntityFromDTO(dto, e);
        return mapper.toDTO(repository.save(e));
    }

    @Transactional
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND, "Fábrica não encontrada");
        }
        repository.deleteById(id);
    }
}
