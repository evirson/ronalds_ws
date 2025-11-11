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

@Service
public class ContasPagarService {

    private final ContasPagarRepository repository;
    private final ContasPagarMapper mapper;

    public ContasPagarService(ContasPagarRepository repository, ContasPagarMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Page<ContasPagarDto> search(String razaoSocial,
                                      String nomeFantasia,
                                      String cnpj,
                                      String telefone,
                                      Integer pagina,
                                      Integer tamanhoPagina ) {

        Specification<ContasPagar> specs = (root, query, cb) -> cb.conjunction();

        if (cnpj != null){
            specs = specs.and((root, query, cb) -> cb.equal(root.get("cnpj"), cnpj));
        }

        if (razaoSocial != null){
            specs = specs.and((root, query, cb) -> cb.like(root.get("razaoSocial"), "%" + razaoSocial.toUpperCase() +"%"));

        }

        if (nomeFantasia != null){
            specs = specs.and((root, query, cb) -> cb.like(root.get("nomeFantasia"), "%" + nomeFantasia.toUpperCase() +"%"));

        }

        if (telefone != null){
            specs = specs.and((root, query, cb) -> cb.equal(root.get("fone"),  telefone));

        }

        Sort sort = Sort.by( "razaoSocial").ascending();

        Pageable pageRequest = PageRequest.of(pagina, tamanhoPagina, sort);

        Page<ContasPagar> pageResult = repository.findAll(specs, pageRequest);

        Page<ContasPagarDto> resultado = pageResult.map(mapper::toDto);

        return resultado;

    }


    public ContasPagarDto findById(Integer id) {
        ContasPagar entity = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "ContasPagar nãoo encontrado"));
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

    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND, "ContasPagar n\u00e3o encontrado");
        }
        repository.deleteById(id);
    }


    
}
