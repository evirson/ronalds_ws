package br.com.vetorsistemas.ronalds_ws.movimento.contasPagar;

import br.com.vetorsistemas.ronalds_ws.movimento.contasPagar.dto.ContasPagarDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/contas-pagar")
public class ContasPagarController {

    private final ContasPagarService service;

    public ContasPagarController(ContasPagarService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<ContasPagarDto>> list(
            @RequestParam(value = "idFornecedor", required = false) Integer idFornecedor,
            @RequestParam(value = "documento", required = false) String documento,
            @RequestParam(value = "descricao", required = false) String descricao,
            @RequestParam(value = "dataVencimentoInicio", required = false)
            @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dataVencimentoInicio,
            @RequestParam(value = "dataVencimentoFim", required = false)
            @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dataVencimentoFim,
            @RequestParam(value = "apenasPendentes", required = false) Boolean apenasPendentes,
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina
    ) {
        return ResponseEntity.ok(service.search(idFornecedor, documento, descricao,
                dataVencimentoInicio, dataVencimentoFim, apenasPendentes, pagina, tamanhoPagina));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContasPagarDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ContasPagarDto> create(@Valid @RequestBody ContasPagarDto body) {
        return ResponseEntity.ok(service.create(body));
    }

    @PostMapping("/lote")
    public ResponseEntity<List<ContasPagarDto>> createLote(@Valid @RequestBody List<ContasPagarDto> body) {
        return ResponseEntity.ok(service.createLote(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContasPagarDto> update(@PathVariable Integer id, @Valid @RequestBody ContasPagarDto body) {
        return ResponseEntity.ok(service.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
