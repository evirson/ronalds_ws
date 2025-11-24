package br.com.vetorsistemas.ronalds_ws.cadastro.produto;

import br.com.vetorsistemas.ronalds_ws.cadastro.produto.dto.ProdutoDto;
import br.com.vetorsistemas.ronalds_ws.cadastro.produto.mapper.ProdutoMapper;
import br.com.vetorsistemas.ronalds_ws.shared.AppException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ProdutoService {
    private final ProdutoRepository repository;
    private final ProdutoMapper mapper;

    public ProdutoService(ProdutoRepository repository, ProdutoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public Page<ProdutoDto> list(String descricao,
                                 String referencia,
                                 Integer idGrupo,
                                 Integer idSubGrupo,
                                 Integer idFabricante,
                                 Integer idFornecedor,
                                 String ncm,
                                 Integer pagina,
                                 Integer tamanhoPagina) {

        if (pagina == null || pagina < 0) pagina = 0;

        if (tamanhoPagina == null || tamanhoPagina <= 0) tamanhoPagina = 10;

        var specs = (org.springframework.data.jpa.domain.Specification<Produto>) (root, query, cb) -> cb.conjunction();

        String desc = normalizeLike(descricao);

        String ref = normalizeLike(referencia);

        String ncmLike = normalizeLike(ncm);

        if (desc != null) specs = specs.and((r,q,cb) -> cb.like(cb.upper(r.get("descricao")), "%"+desc+"%"));

        if (ref != null) specs = specs.and((r,q,cb) -> cb.like(cb.upper(r.get("referencia")), "%"+ref+"%"));

        if (idGrupo != null) specs = specs.and((r,q,cb) -> cb.equal(r.get("idGrupo"), idGrupo));

        if (idSubGrupo != null) specs = specs.and((r,q,cb) -> cb.equal(r.get("idSubGrupo"), idSubGrupo));

        if (idFabricante != null) specs = specs.and((r,q,cb) -> cb.equal(r.get("idFabricante"), idFabricante));

        if (idFornecedor != null) specs = specs.and((r,q,cb) -> cb.equal(r.get("idFornecedor"), idFornecedor));

        if (ncmLike != null) specs = specs.and((r,q,cb) -> cb.like(cb.upper(r.get("ncm")), "%"+ncmLike+"%"));

        Pageable page = PageRequest.of(pagina, tamanhoPagina, Sort.by("descricao").ascending());

        return repository.findAll(specs, page).map(mapper::toDTO);
    }

    private static boolean isBlank(String s){return s==null||s.trim().isEmpty();}
    private static String normalizeLike(String s){if(isBlank(s))return null;return s.trim().toUpperCase();}

    public ProdutoDto findById(Integer id) {
        Produto e = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        return mapper.toDTO(e);
    }

    @Transactional
    public ProdutoDto create(ProdutoDto dto) {
        Produto e = mapper.fromDTO(dto);
        e.setId(null);

        e.setDataCadastro(LocalDateTime.now());

        return mapper.toDTO(repository.save(e));
    }

    @Transactional
    public ProdutoDto update(Integer id, ProdutoDto dto) {
        Produto e = repository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
        mapper.updateEntityFromDTO(dto, e);

        e.setDataAtualizacao(LocalDateTime.now());

        return mapper.toDTO(repository.save(e));
    }

    @Transactional
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new AppException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }
        repository.deleteById(id);
    }
}
