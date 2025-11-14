package br.com.vetorsistemas.ronalds_ws.cadastro.pais;

import br.com.vetorsistemas.ronalds_ws.cadastro.pais.dto.PaisCreateUpdateDTO;
import br.com.vetorsistemas.ronalds_ws.cadastro.pais.dto.PaisDTO;
import br.com.vetorsistemas.ronalds_ws.cadastro.pais.mapper.PaisMapper;
import br.com.vetorsistemas.ronalds_ws.shared.AppException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaisService {

    private final PaisRepository repository;
    private final PaisMapper mapper;

    public PaisService(PaisRepository repository, PaisMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public Page<PaisDTO> search(String nome,
                                Integer pagina,
                                Integer tamanhoPagina) {

        Specification<Pais> specs = (root, query, cb) -> cb.conjunction();

        if (nome != null && !nome.trim().isEmpty()) {
            String nomeUpper = nome.trim().toUpperCase();
            specs = specs.and((root, q, cb) -> cb.like(cb.upper(root.get("nome")), "%" + nomeUpper + "%"));
        }

        if (pagina == null || pagina < 0) pagina = 0;
        if (tamanhoPagina == null || tamanhoPagina <= 0) tamanhoPagina = 10;

        Sort sort = Sort.by("nome").ascending();
        Pageable pageRequest = PageRequest.of(pagina, tamanhoPagina, sort);

        Page<Pais> page = repository.findAll(specs, pageRequest);
        return page.map(mapper::toDTO);
    }

    @Transactional(readOnly = true)
    public PaisDTO findById(Integer id) {
        Pais entity = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "País não encontrado"));
        return mapper.toDTO(entity);
    }

    @Transactional
    public PaisDTO create(PaisCreateUpdateDTO dto) {
        validarPais(dto);
        Pais entity = mapper.fromCreateUpdateDTO(dto);
        return mapper.toDTO(repository.save(entity));
    }

    @Transactional
    public PaisDTO update(Integer id, PaisCreateUpdateDTO dto) {
        validarPais(dto);

        Pais entity = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "País não encontrado"));

        mapper.updateEntityFromDTO(dto, entity);
        return mapper.toDTO(repository.save(entity));
    }

    @Transactional
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND, "País não encontrado");
        }
        repository.deleteById(id);
    }

    private void validarPais(PaisCreateUpdateDTO dto) {
        if (dto.getNome() == null || dto.getNome().trim().isEmpty()) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Nome do país é obrigatório");
        }
    }
}
