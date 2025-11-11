package br.com.vetorsistemas.ronalds_ws.cadastro.empresa;

import br.com.vetorsistemas.ronalds_ws.cadastro.empresa.dto.EmpresaDTO;
import br.com.vetorsistemas.ronalds_ws.cadastro.empresa.mapper.EmpresaMapper;
import br.com.vetorsistemas.ronalds_ws.shared.AppException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpresaService {

    private final EmpresaRepository repository;
    private final EmpresaMapper mapper;

    public EmpresaService(EmpresaRepository repository, EmpresaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public Page<EmpresaDTO> list(String razaoSocial,
                                 String cpfCnpj,
                                 String cidade,
                                 String estado,
                                 Integer pagina,
                                 Integer tamanhoPagina) {
        if (pagina == null || pagina < 0) pagina = 0;
        if (tamanhoPagina == null || tamanhoPagina <= 0) tamanhoPagina = 10;

        var specs = (org.springframework.data.jpa.domain.Specification<Empresa>) (root, query, cb) -> cb.conjunction();

        String raz = normalizeLike(razaoSocial);
        String doc = sanitizeDigits(cpfCnpj);
        String cid = normalizeLike(cidade);
        String uf = normalizeLike(estado);

        if (raz != null) specs = specs.and((r,q,cb) -> cb.like(cb.upper(r.get("razaoSocial")), "%"+raz+"%"));
        if (doc != null) specs = specs.and((r,q,cb) -> cb.equal(r.get("cpfCnpj"), doc));
        if (cid != null) specs = specs.and((r,q,cb) -> cb.like(cb.upper(r.get("cidade")), "%"+cid+"%"));
        if (uf != null) specs = specs.and((r,q,cb) -> cb.like(cb.upper(r.get("estado")), "%"+uf+"%"));

        Pageable page = PageRequest.of(pagina, tamanhoPagina, Sort.by("razaoSocial").ascending());
        return repository.findAll(specs, page).map(mapper::toDTO);
    }

    private static boolean isBlank(String s) { return s == null || s.trim().isEmpty(); }
    private static String sanitizeDigits(String s) { if (isBlank(s)) return null; String d = s.replaceAll("[^0-9]","" ); return d.isEmpty()? null: d; }
    private static String normalizeLike(String s) { if (isBlank(s)) return null; return s.trim().toUpperCase(); }

    @Transactional(readOnly = true)
    public EmpresaDTO findById(Integer id) {
        Empresa e = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Empresa não encontrada"));
        return mapper.toDTO(e);
    }

    @Transactional
    public EmpresaDTO create(EmpresaDTO dto) {
        Empresa e = mapper.fromDTO(dto);
        e.setId(null);
        return mapper.toDTO(repository.save(e));
    }

    @Transactional
    public EmpresaDTO update(Integer id, EmpresaDTO dto) {
        Empresa e = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Empresa não encontrada"));
        mapper.updateEntityFromDTO(dto, e);
        return mapper.toDTO(repository.save(e));
    }

    @Transactional
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND, "Empresa não encontrada");
        }
        repository.deleteById(id);
    }
}
