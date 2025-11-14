package br.com.vetorsistemas.ronalds_ws.cadastro.grupo;

import br.com.vetorsistemas.ronalds_ws.cadastro.grupo.dto.GrupoDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:5173","http://localhost:4200"}, allowCredentials = "false")
@RestController
@RequestMapping("/api/cadastros/grupos")
public class GrupoController {

    private final GrupoService service;

    public GrupoController(GrupoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<GrupoDto>> list(
            @RequestParam(value = "nomeGrupo", required = false) String nomeGrupo,
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina
    ) {
        return ResponseEntity.ok(service.list(nomeGrupo, pagina, tamanhoPagina));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GrupoDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<GrupoDto> create(@Valid @RequestBody GrupoDto body) {
        return ResponseEntity.ok(service.create(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GrupoDto> update(@PathVariable Integer id, @Valid @RequestBody GrupoDto body) {
        return ResponseEntity.ok(service.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
