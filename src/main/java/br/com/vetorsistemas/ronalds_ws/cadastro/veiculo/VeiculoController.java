package br.com.vetorsistemas.ronalds_ws.cadastro.veiculo;

import br.com.vetorsistemas.ronalds_ws.cadastro.veiculo.dto.VeiculoDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {

    private final VeiculoService service;

    public VeiculoController(VeiculoService service) {
        this.service = service;
    }

    @GetMapping
    @CrossOrigin
    public ResponseEntity<Page<VeiculoDto>> search(
            @RequestParam(required = false) String placa,
            @RequestParam(required = false, name = "tipo") String tipo,
            @RequestParam(required = false) String modelo,
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
