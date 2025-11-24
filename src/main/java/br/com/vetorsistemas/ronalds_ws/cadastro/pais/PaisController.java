package br.com.vetorsistemas.ronalds_ws.cadastro.pais;

import br.com.vetorsistemas.ronalds_ws.cadastro.pais.dto.PaisCreateUpdateDTO;
import br.com.vetorsistemas.ronalds_ws.cadastro.pais.dto.PaisDTO;
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
@RequestMapping("/api/cadastros/paises")
@Tag(name = "Países", description = "Endpoints para gerenciamento de Países")
public class PaisController {

    private final PaisService service;

    public PaisController(PaisService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<PaisDTO>> list(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina
    ) {
        return ResponseEntity.ok(service.search(nome, pagina, tamanhoPagina));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaisDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<PaisDTO> create(@Valid @RequestBody PaisCreateUpdateDTO body) {
        return ResponseEntity.ok(service.create(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaisDTO> update(@PathVariable Integer id,
                                          @Valid @RequestBody PaisCreateUpdateDTO body) {
        return ResponseEntity.ok(service.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
