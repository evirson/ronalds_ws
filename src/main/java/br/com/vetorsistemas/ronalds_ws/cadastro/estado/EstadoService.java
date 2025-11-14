package br.com.vetorsistemas.ronalds_ws.cadastro.estado;

import br.com.vetorsistemas.ronalds_ws.cadastro.estado.dto.EstadoCreateUpdateDTO;
import br.com.vetorsistemas.ronalds_ws.cadastro.estado.dto.EstadoDTO;
import br.com.vetorsistemas.ronalds_ws.cadastro.estado.mapper.EstadoMapper;
import br.com.vetorsistemas.ronalds_ws.shared.AppException;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstadoService {

    private final EstadoRepository repository;
    private final EstadoMapper mapper;
    private final EntityManager entityManager;

    public EstadoService(EstadoRepository repository, EstadoMapper mapper, EntityManager entityManager) {
        this.repository = repository;
        this.mapper = mapper;
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public Page<EstadoDTO> search(String sigla,
                                  String nome,
                                  Integer codigoPais,
                                  Integer pagina,
                                  Integer tamanhoPagina) {

        Specification<Estado> specs = (root, query, cb) -> cb.conjunction();

        if (sigla != null && !sigla.trim().isEmpty()) {
            String siglaUpper = sigla.trim().toUpperCase();
            specs = specs.and((root, q, cb) -> cb.like(cb.upper(root.get("sigla")), "%" + siglaUpper + "%"));
        }

        if (nome != null && !nome.trim().isEmpty()) {
            String nomeUpper = nome.trim().toUpperCase();
            specs = specs.and((root, q, cb) -> cb.like(cb.upper(root.get("nome")), "%" + nomeUpper + "%"));
        }

        if (codigoPais != null) {
            specs = specs.and((root, q, cb) -> cb.equal(root.get("codigoPais"), codigoPais));
        }

        if (pagina == null || pagina < 0) pagina = 0;
        if (tamanhoPagina == null || tamanhoPagina <= 0) tamanhoPagina = 10;

        Sort sort = Sort.by("nome").ascending();
        Pageable pageRequest = PageRequest.of(pagina, tamanhoPagina, sort);

        Page<Estado> page = repository.findAll(specs, pageRequest);
        return page.map(mapper::toDTO);
    }

    @Transactional(readOnly = true)
    public EstadoDTO findById(Integer id) {
        Estado entity = entityManager.createQuery(
                "SELECT e FROM Estado e " +
                "LEFT JOIN FETCH e.pais " +
                "WHERE e.id = :id", Estado.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst()
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Estado não encontrado"));

        return mapper.toDTO(entity);
    }

    @Transactional
    public EstadoDTO create(EstadoCreateUpdateDTO dto) {
        validarEstado(dto);
        Estado entity = mapper.fromCreateUpdateDTO(dto);
        Estado saved = repository.save(entity);
        return findById(saved.getId());
    }

    @Transactional
    public EstadoDTO update(Integer id, EstadoCreateUpdateDTO dto) {
        validarEstado(dto);

        Estado entity = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Estado não encontrado"));

        mapper.updateEntityFromDTO(dto, entity);
        repository.save(entity);

        return findById(id);
    }

    @Transactional
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND, "Estado não encontrado");
        }
        repository.deleteById(id);
    }

    private void validarEstado(EstadoCreateUpdateDTO dto) {
        if (dto.getNome() == null || dto.getNome().trim().isEmpty()) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Nome do estado é obrigatório");
        }

        if (dto.getSigla() == null || dto.getSigla().trim().isEmpty()) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Sigla do estado é obrigatória");
        }

        if (dto.getSigla().length() > 2) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Sigla do estado deve ter no máximo 2 caracteres");
        }
    }
}
