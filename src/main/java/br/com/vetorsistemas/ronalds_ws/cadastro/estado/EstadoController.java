package br.com.vetorsistemas.ronalds_ws.cadastro.estado;

import br.com.vetorsistemas.ronalds_ws.cadastro.estado.dto.EstadoCreateUpdateDTO;
import br.com.vetorsistemas.ronalds_ws.cadastro.estado.dto.EstadoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cadastros/estados")
@Tag(name = "Estados", description = "Endpoints para gerenciamento de Estados")
public class EstadoController {

    private final EstadoService service;

    public EstadoController(EstadoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<EstadoDTO>> list(
            @RequestParam(value = "sigla", required = false) String sigla,
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "codigoPais", required = false) Integer codigoPais,
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina
    ) {
        return ResponseEntity.ok(service.search(sigla, nome, codigoPais, pagina, tamanhoPagina));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<EstadoDTO> create(@Valid @RequestBody EstadoCreateUpdateDTO body) {
        return ResponseEntity.ok(service.create(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoDTO> update(@PathVariable Integer id,
                                            @Valid @RequestBody EstadoCreateUpdateDTO body) {
        return ResponseEntity.ok(service.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
