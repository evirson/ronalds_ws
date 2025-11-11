package br.com.vetorsistemas.ronalds_ws.movimento.contaCorrente;

import br.com.vetorsistemas.ronalds_ws.movimento.contaCorrente.dto.ContaCorrenteDto;
import br.com.vetorsistemas.ronalds_ws.movimento.contaCorrente.mapper.ContaCorrenteMapper;
import br.com.vetorsistemas.ronalds_ws.shared.AppException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContaCorrenteService {
    private final ContaCorrenteRepository repository;
    private final ContaCorrenteMapper mapper;

    public ContaCorrenteService(ContaCorrenteRepository repository, ContaCorrenteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public Page<ContaCorrenteDto> list(String descricao,
                                       Integer banco,
                                       Integer agencia,
                                       Integer conta,
                                       Integer pagina,
                                       Integer tamanhoPagina) {
        if (pagina == null || pagina < 0) pagina = 0;
        if (tamanhoPagina == null || tamanhoPagina <= 0) tamanhoPagina = 10;
        var specs = (org.springframework.data.jpa.domain.Specification<ContaCorrente>) (root, query, cb) -> cb.conjunction();
        String desc = normalizeLike(descricao);
        if (desc != null) specs = specs.and((r,q,cb) -> cb.like(cb.upper(r.get("descricao")), "%"+desc+"%"));
        if (banco != null) specs = specs.and((r,q,cb) -> cb.equal(r.get("banco"), banco));
        if (agencia != null) specs = specs.and((r,q,cb) -> cb.equal(r.get("agencia"), agencia));
        if (conta != null) specs = specs.and((r,q,cb) -> cb.equal(r.get("conta"), conta));
        Pageable page = PageRequest.of(pagina, tamanhoPagina, Sort.by("descricao").ascending());
        return repository.findAll(specs, page).map(mapper::toDTO);
    }

    private static boolean isBlank(String s){return s==null||s.trim().isEmpty();}
    private static String normalizeLike(String s){if(isBlank(s))return null;return s.trim().toUpperCase();}

    public ContaCorrenteDto findById(Integer id) {
        ContaCorrente e = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Conta corrente não encontrada"));
        return mapper.toDTO(e);
    }

    @Transactional
    public ContaCorrenteDto create(ContaCorrenteDto dto) {
        ContaCorrente e = mapper.fromDTO(dto);
        e.setId(null);
        return mapper.toDTO(repository.save(e));
    }

    @Transactional
    public ContaCorrenteDto update(Integer id, ContaCorrenteDto dto) {
        ContaCorrente e = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Conta corrente não encontrada"));
        mapper.updateEntityFromDTO(dto, e);
        return mapper.toDTO(repository.save(e));
    }

    @Transactional
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND, "Conta corrente não encontrada");
        }
        repository.deleteById(id);
    }
}
