package br.com.vetorsistemas.ronalds_ws.cadastro.municipio;

import br.com.vetorsistemas.ronalds_ws.cadastro.municipio.dto.MunicipioDto;
import br.com.vetorsistemas.ronalds_ws.cadastro.municipio.mapper.MunicipioMapper;
import br.com.vetorsistemas.ronalds_ws.shared.AppException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MunicipioService {
    private final MunicipioRepository repository;
    private final MunicipioMapper mapper;

    public MunicipioService(MunicipioRepository repository, MunicipioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public Page<MunicipioDto> list(Integer codigoEstado,
                                   Integer municipio,
                                   Integer pagina,
                                   Integer tamanhoPagina) {
        if (pagina == null || pagina < 0) pagina = 0;
        if (tamanhoPagina == null || tamanhoPagina <= 0) tamanhoPagina = 10;
        var specs = (org.springframework.data.jpa.domain.Specification<Municipio>) (root, query, cb) -> cb.conjunction();
        if (codigoEstado != null) specs = specs.and((r,q,cb) -> cb.equal(r.get("codigoEstado"), codigoEstado));
        if (municipio != null) specs = specs.and((r,q,cb) -> cb.equal(r.get("municipio"), municipio));
        Pageable page = PageRequest.of(pagina, tamanhoPagina, Sort.by("id").ascending());
        return repository.findAll(specs, page).map(mapper::toDTO);
    }

    public MunicipioDto findById(Integer id) {
        Municipio e = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Município não encontrado"));
        return mapper.toDTO(e);
    }

    @Transactional
    public MunicipioDto create(MunicipioDto dto) {
        Municipio e = mapper.fromDTO(dto);
        e.setId(null);
        return mapper.toDTO(repository.save(e));
    }

    @Transactional
    public MunicipioDto update(Integer id, MunicipioDto dto) {
        Municipio e = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Município não encontrado"));
        mapper.updateEntityFromDTO(dto, e);
        return mapper.toDTO(repository.save(e));
    }

    @Transactional
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND, "Município não encontrado");
        }
        repository.deleteById(id);
    }
}
