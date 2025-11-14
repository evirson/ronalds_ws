package br.com.vetorsistemas.ronalds_ws.estoque;

import br.com.vetorsistemas.ronalds_ws.estoque.dto.EstoqueProdutoDto;
import br.com.vetorsistemas.ronalds_ws.estoque.mapper.EstoqueProdutoMapper;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstoqueProdutoService {
    private final EstoqueProdutoRepository repository;
    private final EstoqueProdutoMapper mapper;

    public EstoqueProdutoService(EstoqueProdutoRepository repository, EstoqueProdutoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public Page<EstoqueProdutoDto> list(String termo,
                                        Integer pagina,
                                        Integer tamanhoPagina,
                                        String ordenarPor,
                                        String direcao) {

        if (pagina == null || pagina < 0) pagina = 0;

        if (tamanhoPagina == null || tamanhoPagina <= 0) tamanhoPagina = 10;

        if (ordenarPor == null || ordenarPor.isBlank()) ordenarPor = "nome"; // nome = produto.descricao

        if (direcao == null || direcao.isBlank()) direcao = "asc";

        Specification<EstoqueProduto> specs = (root, query, cb) -> {
            // join to allow filtering/sorting by product name
            root.join("produto", JoinType.LEFT);
            query.distinct(true);
            return cb.conjunction();
        };

        String t = normalizeLike(termo);
        if (t != null) {
            specs = specs.and((root, query, cb) -> {
                var join = root.join("produto", JoinType.LEFT);
                var likeNome = cb.like(cb.upper(join.get("descricao")), "%" + t + "%");
                Specification<EstoqueProduto> byNome = (r, q, c) -> likeNome;
                Specification<EstoqueProduto> byCodigoEq = (r, q, c) -> {
                    try {
                        Integer codigo = Integer.valueOf(termo.trim());
                        return c.equal(r.get("id"), codigo);
                    } catch (NumberFormatException ex) {
                        return c.disjunction();
                    }
                };
                return byNome.or(byCodigoEq).toPredicate(root, query, cb);
            });
        }

        Sort sort = buildSort(ordenarPor, direcao);
        Pageable page = PageRequest.of(pagina, tamanhoPagina, sort);
        return repository.findAll(specs, page).map(mapper::toDTO);
    }

    private static boolean isBlank(String s) { return s == null || s.trim().isEmpty(); }
    private static String normalizeLike(String s) { if (isBlank(s)) return null; return s.trim().toUpperCase(); }

    private static Sort buildSort(String ordenarPor, String direcao) {
        Sort.Direction dir = "desc".equalsIgnoreCase(direcao) ? Sort.Direction.DESC : Sort.Direction.ASC;
        String prop;
        switch (ordenarPor.toLowerCase()) {
            case "codigo":
            case "id":
                prop = "id";
                break;
            case "qtd":
            case "quantidade":
            case "quantidadeestoque":
                prop = "quantidadeEstoque";
                break;
            case "nome":
            case "produto":
            case "descricao":
            default:
                // nested sort by associated product name
                prop = "produto.descricao";
        }
        return Sort.by(new Sort.Order(dir, prop));
    }
}

