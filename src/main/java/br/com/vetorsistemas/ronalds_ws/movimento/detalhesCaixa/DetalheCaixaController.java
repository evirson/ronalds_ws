package br.com.vetorsistemas.ronalds_ws.movimento.detalhesCaixa;

import br.com.vetorsistemas.ronalds_ws.movimento.detalhesCaixa.dto.DetalheCaixaDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:5173","http://localhost:4200"}, allowCredentials = "false")
@RestController
@RequestMapping("/api/movimento/detalhes-caixa")
public class DetalheCaixaController {

    private final DetalheCaixaService service;

    public DetalheCaixaController(DetalheCaixaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<DetalheCaixaDto>> list(
            @RequestParam(value = "planoContas", required = false) String planoContas,
            @RequestParam(value = "numeroDocumento", required = false) String numeroDocumento,
            @RequestParam(value = "historico", required = false) String historico,
            @RequestParam(value = "dataMovimentoDe", required = false) String dataMovimentoDe,
            @RequestParam(value = "dataMovimentoAte", required = false) String dataMovimentoAte,
            @RequestParam(value = "valorMovimentoDe", required = false) Double valorMovimentoDe,
            @RequestParam(value = "valorMovimentoAte", required = false) Double valorMovimentoAte,
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina
    ) {
        LocalDateTime de = parseDate(dataMovimentoDe);
        LocalDateTime ate = parseDate(dataMovimentoAte);
        return ResponseEntity.ok(service.list(planoContas, numeroDocumento, historico, de, ate, valorMovimentoDe, valorMovimentoAte, pagina, tamanhoPagina));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalheCaixaDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<DetalheCaixaDto> create(@Valid @RequestBody DetalheCaixaDto body) {
        return ResponseEntity.ok(service.create(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalheCaixaDto> update(@PathVariable Integer id, @Valid @RequestBody DetalheCaixaDto body) {
        return ResponseEntity.ok(service.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private static LocalDateTime parseDate(String value) {
        if (value == null || value.isBlank()) return null;
        return LocalDateTime.parse(value);
    }
}
