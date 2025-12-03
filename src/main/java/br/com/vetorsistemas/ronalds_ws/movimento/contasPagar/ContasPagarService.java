package br.com.vetorsistemas.ronalds_ws.movimento.contasPagar;

import br.com.vetorsistemas.ronalds_ws.movimento.contasPagar.dto.ContasPagarDto;
import br.com.vetorsistemas.ronalds_ws.movimento.contasPagar.mapper.ContasPagarMapper;
import br.com.vetorsistemas.ronalds_ws.shared.AppException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContasPagarService {

    private final ContasPagarRepository repository;
    private final ContasPagarMapper mapper;

    public ContasPagarService(ContasPagarRepository repository, ContasPagarMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public Page<ContasPagarDto> search(Integer idFornecedor,
                                       String documento,
                                       String descricao,
                                       LocalDate dataVencimentoInicio,
                                       LocalDate dataVencimentoFim,
                                       Boolean apenasPendentes,
                                       Integer pagina,
                                       Integer tamanhoPagina) {

        if (pagina == null || pagina < 0) pagina = 0;
        if (tamanhoPagina == null || tamanhoPagina <= 0) tamanhoPagina = 10;

        Specification<ContasPagar> specs = (root, query, cb) -> cb.conjunction();

        if (idFornecedor != null) {
            specs = specs.and((root, query, cb) -> cb.equal(root.get("idFornecedor"), idFornecedor));
        }

        if (documento != null && !documento.trim().isEmpty()) {
            String doc = documento.trim().toUpperCase();
            specs = specs.and((root, query, cb) -> cb.like(cb.upper(root.get("documento")), "%" + doc + "%"));
        }

        if (descricao != null && !descricao.trim().isEmpty()) {
            String desc = descricao.trim().toUpperCase();
            specs = specs.and((root, query, cb) -> cb.like(cb.upper(root.get("descricao")), "%" + desc + "%"));
        }

        if (dataVencimentoInicio != null) {
            LocalDateTime inicio = dataVencimentoInicio.atStartOfDay();
            specs = specs.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("dataVencimento"), inicio));
        }

        if (dataVencimentoFim != null) {
            LocalDateTime fim = dataVencimentoFim.atTime(LocalTime.MAX);
            specs = specs.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("dataVencimento"), fim));
        }

        if (apenasPendentes != null && apenasPendentes) {
            specs = specs.and((root, query, cb) -> cb.or(
                    cb.isNull(root.get("valorPago")),
                    cb.equal(root.get("valorPago"), 0.0)
            ));
        }

        Sort sort = Sort.by("dataVencimento").ascending();
        Pageable pageRequest = PageRequest.of(pagina, tamanhoPagina, sort);

        return repository.findAll(specs, pageRequest).map(mapper::toDto);
    }


    public ContasPagarDto findById(Integer id) {
        ContasPagar entity = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "ContasPagar não encontrado"));
        return mapper.toDto(entity);
    }

    @Transactional
    public ContasPagarDto create(ContasPagarDto dto) {

        ContasPagar entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    public ContasPagarDto update(Integer id, ContasPagarDto dto) {
        ContasPagar entity = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "ContasPagar n\u00e3o encontrado"));

        mapper.updateEntityFromDTO(dto, entity);

        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND, "ContasPagar não encontrado");
        }
        repository.deleteById(id);
    }

    @Transactional
    public List<ContasPagarDto> createLote(List<ContasPagarDto> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Lista de contas a pagar não pode ser vazia");
        }

        List<ContasPagar> entities = dtos.stream()
                .map(mapper::toEntity)
                .collect(Collectors.toList());

        List<ContasPagar> saved = repository.saveAll(entities);

        return saved.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
