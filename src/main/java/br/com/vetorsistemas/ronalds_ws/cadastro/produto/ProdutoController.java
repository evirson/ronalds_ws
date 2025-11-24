package br.com.vetorsistemas.ronalds_ws.cadastro.produto;

import br.com.vetorsistemas.ronalds_ws.cadastro.produto.dto.ProdutoDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoDto>> list(
            @RequestParam(value = "descricao", required = false) String descricao,
            @RequestParam(value = "referencia", required = false) String referencia,
            @RequestParam(value = "idGrupo", required = false) Integer idGrupo,
            @RequestParam(value = "idSubGrupo", required = false) Integer idSubGrupo,
            @RequestParam(value = "idFabricante", required = false) Integer idFabricante,
            @RequestParam(value = "idFornecedor", required = false) Integer idFornecedor,
            @RequestParam(value = "ncm", required = false) String ncm,
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina
    ) {
        return ResponseEntity.ok(service.list(descricao, referencia, idGrupo, idSubGrupo, idFabricante, idFornecedor, ncm, pagina, tamanhoPagina));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> create(@Valid @RequestBody ProdutoDto body) {
        return ResponseEntity.ok(service.create(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDto> update(@PathVariable Integer id, @Valid @RequestBody ProdutoDto body) {
        return ResponseEntity.ok(service.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
