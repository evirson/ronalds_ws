package br.com.vetorsistemas.ronalds_ws.cadastro.fornecedor;

import br.com.vetorsistemas.ronalds_ws.shared.AppException;
import br.com.vetorsistemas.ronalds_ws.cadastro.fornecedor.dto.FornecedorCreateUpdateDTO;
import br.com.vetorsistemas.ronalds_ws.cadastro.fornecedor.dto.FornecedorDTO;
import br.com.vetorsistemas.ronalds_ws.cadastro.fornecedor.mapper.FornecedorMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FornecedorService  {

    private final FornecedorRepository repository;
    private final FornecedorMapper mapper;

    public FornecedorService(FornecedorRepository repository, FornecedorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public Page<FornecedorDTO> search(String razaoSocial,
                                      String nomeFantasia,
                                      String cnpj,
                                      String telefone,
                                      Integer pagina,
                                      Integer tamanhoPagina ) {

        Specification<Fornecedor> specs = (root, query, cb) -> cb.conjunction();

        String cnpjSanitized = sanitizeDigits(cnpj);
        String telSanitized = sanitizeDigits(telefone);
        String razao = normalizeLike(razaoSocial);
        String fantasia = normalizeLike(nomeFantasia);

        if (cnpjSanitized != null) {
            specs = specs.and((root, query, cb) -> cb.equal(root.get("cnpj"), cnpjSanitized));
        }

        if (razao != null) {
            specs = specs.and((root, query, cb) -> cb.like(cb.upper(root.get("razaoSocial")), "%" + razao + "%"));
        }

        if (fantasia != null) {
            specs = specs.and((root, query, cb) -> cb.like(cb.upper(root.get("nomeFantasia")), "%" + fantasia + "%"));
        }

        if (telSanitized != null) {
            specs = specs.and((root, query, cb) -> cb.equal(root.get("fone"), telSanitized));
        }

        if (pagina == null || pagina < 0) pagina = 0;
        if (tamanhoPagina == null || tamanhoPagina <= 0) tamanhoPagina = 10;

        Sort sort = Sort.by("razaoSocial").ascending();

        Pageable pageRequest = PageRequest.of(pagina, tamanhoPagina, sort);

        Page<Fornecedor> pageResult = repository.findAll(specs, pageRequest);

        Page<FornecedorDTO> resultado = pageResult.map(mapper::toDTO);

        return resultado;

    }

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    private static String sanitizeDigits(String s) {
        if (isBlank(s)) return null;
        String onlyDigits = s.replaceAll("[^0-9]", "");
        return onlyDigits.isEmpty() ? null : onlyDigits;
    }

    private static String normalizeLike(String s) {
        if (isBlank(s)) return null;
        return s.trim().toUpperCase();
    }


    public FornecedorDTO findById(Integer id) {
        Fornecedor entity = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Fornecedor n\u00e3o encontrado"));
        return mapper.toDTO(entity);
    }

    @Transactional
    public FornecedorDTO create(FornecedorCreateUpdateDTO dto) {

        //tratar DTO só numeros e nulos

        Fornecedor entity = mapper.fromCreateUpdateDTO(dto);

        entity.setId(null);

        return mapper.toDTO(repository.save(entity));
    }

    public FornecedorDTO update(Integer id, FornecedorCreateUpdateDTO dto) {
        Fornecedor entity = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Fornecedor n\u00e3o encontrado"));

        mapper.updateEntityFromDTO(dto, entity);

        return mapper.toDTO(repository.save(entity));
    }

    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND, "Fornecedor n\u00e3o encontrado");
        }
        repository.deleteById(id);
    }


    
}
