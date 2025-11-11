package br.com.vetorsistemas.ronalds_ws.cadastro.planoContas;

import br.com.vetorsistemas.ronalds_ws.cadastro.planoContas.dto.PlanoContasDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cadastros/planos-contas")
public class PlanoContasController {

    private final PlanoContasService service;

    public PlanoContasController(PlanoContasService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<PlanoContasDto>> list(
            @RequestParam(value = "descricao", required = false) String descricao,
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina
    ) {
        return ResponseEntity.ok(service.list(descricao, pagina, tamanhoPagina));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanoContasDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<PlanoContasDto> create(@Valid @RequestBody PlanoContasDto body) {
        return ResponseEntity.ok(service.create(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanoContasDto> update(@PathVariable String id, @Valid @RequestBody PlanoContasDto body) {
        return ResponseEntity.ok(service.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
