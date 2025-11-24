package br.com.vetorsistemas.ronalds_ws.movimento.contaCorrente;

import br.com.vetorsistemas.ronalds_ws.movimento.contaCorrente.dto.ContaCorrenteDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cadastros/contas-correntes")
public class ContaCorrenteController {

    private final ContaCorrenteService service;

    public ContaCorrenteController(ContaCorrenteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<ContaCorrenteDto>> list(
            @RequestParam(value = "descricao", required = false) String descricao,
            @RequestParam(value = "banco", required = false) Integer banco,
            @RequestParam(value = "agencia", required = false) Integer agencia,
            @RequestParam(value = "conta", required = false) Integer conta,
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina
    ) {
        return ResponseEntity.ok(service.list(descricao, banco, agencia, conta, pagina, tamanhoPagina));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaCorrenteDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ContaCorrenteDto> create(@Valid @RequestBody ContaCorrenteDto body) {
        return ResponseEntity.ok(service.create(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContaCorrenteDto> update(@PathVariable Integer id, @Valid @RequestBody ContaCorrenteDto body) {
        return ResponseEntity.ok(service.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
