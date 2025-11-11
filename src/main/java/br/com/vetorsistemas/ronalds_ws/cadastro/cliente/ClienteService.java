package br.com.vetorsistemas.ronalds_ws.cadastro.cliente;

import br.com.vetorsistemas.ronalds_ws.cadastro.cliente.dto.ClienteCreateUpdateDTO;
import br.com.vetorsistemas.ronalds_ws.cadastro.cliente.dto.ClienteDTO;
import br.com.vetorsistemas.ronalds_ws.cadastro.cliente.mapper.ClienteMapper;
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
public class ClienteService {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    public ClienteService(ClienteRepository repository, ClienteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public Page<ClienteDTO> search(String cnpcpf,
                                   String raznom,
                                   String fannat,
                                   String telcli,
                                   String celcli,
                                   String maicli,
                                   Integer pagina,
                                   Integer tamanhoPagina) {

        Specification<Cliente> specs = (root, query, cb) -> cb.conjunction();

        String doc = sanitizeDigits(cnpcpf);
        String tel = sanitizeDigits(telcli);
        String cel = sanitizeDigits(celcli);
        String raz = normalizeLike(raznom);
        String fan = normalizeLike(fannat);
        String mail = normalizeLike(maicli);

        if (doc != null) {
            specs = specs.and((root, q, cb) -> cb.equal(root.get("cnpjCpf"), doc));
        }
        if (raz != null) {
            specs = specs.and((root, q, cb) -> cb.like(cb.upper(root.get("nomeRazaoSocial")), "%" + raz + "%"));
        }
        if (fan != null) {
            specs = specs.and((root, q, cb) -> cb.like(cb.upper(root.get("nomeFantasia")), "%" + fan + "%"));
        }
        if (tel != null) {
            specs = specs.and((root, q, cb) -> cb.equal(root.get("telefone"), tel));
        }
        if (cel != null) {
            specs = specs.and((root, q, cb) -> cb.equal(root.get("celular"), cel));
        }
        if (mail != null) {
            specs = specs.and((root, q, cb) -> cb.like(cb.upper(root.get("email")), "%" + mail + "%"));
        }

        if (pagina == null || pagina < 0) pagina = 0;
        if (tamanhoPagina == null || tamanhoPagina <= 0) tamanhoPagina = 10;

        Sort sort = Sort.by("nomeRazaoSocial").ascending();
        Pageable pageRequest = PageRequest.of(pagina, tamanhoPagina, sort);

        Page<Cliente> page = repository.findAll(specs, pageRequest);
        return page.map(mapper::toDTO);
    }

    public ClienteDTO findById(Integer id) {
        Cliente entity = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        return mapper.toDTO(entity);
    }

    @Transactional
    public ClienteDTO create(ClienteCreateUpdateDTO dto) {
        Cliente entity = mapper.fromCreateUpdateDTO(dto);
        return mapper.toDTO(repository.save(entity));
    }

    @Transactional
    public ClienteDTO update(Integer id, ClienteCreateUpdateDTO dto) {
        Cliente entity = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        mapper.updateEntityFromDTO(dto, entity);
        return mapper.toDTO(repository.save(entity));
    }

    @Transactional
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
        }
        repository.deleteById(id);
    }

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    private static String sanitizeDigits(String s) {
        if (isBlank(s)) return null;
        String only = s.replaceAll("[^0-9]", "");
        return only.isEmpty() ? null : only;
    }

    private static String normalizeLike(String s) {
        if (isBlank(s)) return null;
        return s.trim().toUpperCase();
    }
}
