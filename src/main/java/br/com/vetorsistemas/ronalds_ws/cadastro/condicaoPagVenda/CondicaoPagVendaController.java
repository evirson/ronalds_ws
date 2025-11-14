package br.com.vetorsistemas.ronalds_ws.cadastro.condicaoPagVenda;

import br.com.vetorsistemas.ronalds_ws.cadastro.condicaoPagVenda.dto.CondicaoPagVendaDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:5173","http://localhost:4200"}, allowCredentials = "false")
@RestController
@RequestMapping("/api/cadastros/condicoes-pag-venda")
public class CondicaoPagVendaController {

    private final CondicaoPagVendaService service;

    public CondicaoPagVendaController(CondicaoPagVendaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<CondicaoPagVendaDto>> list(
            @RequestParam(value = "descricao", required = false) String descricao,
            @RequestParam(value = "quantidadeParcelas", required = false) Integer quantidadeParcelas,
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina
    ) {
        return ResponseEntity.ok(service.list(descricao, quantidadeParcelas, pagina, tamanhoPagina));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CondicaoPagVendaDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<CondicaoPagVendaDto> create(@Valid @RequestBody CondicaoPagVendaDto body) {
        return ResponseEntity.ok(service.create(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CondicaoPagVendaDto> update(@PathVariable Integer id, @Valid @RequestBody CondicaoPagVendaDto body) {
        return ResponseEntity.ok(service.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
