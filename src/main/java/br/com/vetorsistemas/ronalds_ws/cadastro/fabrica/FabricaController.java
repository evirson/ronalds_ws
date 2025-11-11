package br.com.vetorsistemas.ronalds_ws.cadastro.fabrica;

import br.com.vetorsistemas.ronalds_ws.cadastro.fabrica.dto.FabricaDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cadastros/fabricas")
public class FabricaController {

    private final FabricaService service;

    public FabricaController(FabricaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<FabricaDto>> list(
            @RequestParam(value = "nomeFabricante", required = false) String nomeFabricante,
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina
    ) {
        return ResponseEntity.ok(service.list(nomeFabricante, pagina, tamanhoPagina));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FabricaDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<FabricaDto> create(@Valid @RequestBody FabricaDto body) {
        return ResponseEntity.ok(service.create(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FabricaDto> update(@PathVariable Integer id, @Valid @RequestBody FabricaDto body) {
        return ResponseEntity.ok(service.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
