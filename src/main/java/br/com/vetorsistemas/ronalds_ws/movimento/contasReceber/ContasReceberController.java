package br.com.vetorsistemas.ronalds_ws.movimento.contasReceber;

import br.com.vetorsistemas.ronalds_ws.movimento.contasReceber.dto.ContasReceberDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cadastros/contas-receber")
public class ContasReceberController {

    private final ContasReceberService service;

    public ContasReceberController(ContasReceberService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<ContasReceberDto>> list(
            @RequestParam(value = "idCliente", required = false) Integer idCliente,
            @RequestParam(value = "documento", required = false) String documento,
            @RequestParam(value = "previs", required = false) String previs,
            @RequestParam(value = "dataMovimentoDe", required = false) String dataMovimentoDe,
            @RequestParam(value = "dataMovimentoAte", required = false) String dataMovimentoAte,
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina
    ) {
        java.time.LocalDateTime de = dataMovimentoDe != null && !dataMovimentoDe.isBlank() ? java.time.LocalDateTime.parse(dataMovimentoDe) : null;
        java.time.LocalDateTime ate = dataMovimentoAte != null && !dataMovimentoAte.isBlank() ? java.time.LocalDateTime.parse(dataMovimentoAte) : null;
        return ResponseEntity.ok(service.list(idCliente, documento, previs, de, ate, pagina, tamanhoPagina));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContasReceberDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ContasReceberDto> create(@Valid @RequestBody ContasReceberDto body) {
        return ResponseEntity.ok(service.create(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContasReceberDto> update(@PathVariable Integer id, @Valid @RequestBody ContasReceberDto body) {
        return ResponseEntity.ok(service.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
