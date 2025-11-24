package br.com.vetorsistemas.ronalds_ws.cadastro.veiculo;

import br.com.vetorsistemas.ronalds_ws.cadastro.veiculo.dto.VeiculoDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/api/veiculos")
public class VeiculoController {

    private final VeiculoService service;

    public VeiculoController(VeiculoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<VeiculoDto>> search(
            @RequestParam(required = false) @Size(max = 8) String placa,
            @RequestParam(required = false, name = "tipo") @Size(max = 25) String tipo,
            @RequestParam(required = false) @Size(max = 50) String modelo,
            @RequestParam(defaultValue = "0") Integer pagina,
            @RequestParam(defaultValue = "20") Integer tamanho) {
        return ResponseEntity.ok(service.search(placa, tipo, modelo, pagina, tamanho));
    }

    @GetMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<VeiculoDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @CrossOrigin
    public ResponseEntity<VeiculoDto> create(@Valid @RequestBody VeiculoDto body) {
        return ResponseEntity.ok(service.create(body));
    }

    @PutMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<VeiculoDto> update(@PathVariable Integer id, @Valid @RequestBody VeiculoDto body) {
        return ResponseEntity.ok(service.update(id, body));
    }

    @DeleteMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
