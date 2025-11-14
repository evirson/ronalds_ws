package br.com.vetorsistemas.ronalds_ws.cadastro.subGrupo;

import br.com.vetorsistemas.ronalds_ws.cadastro.subGrupo.dto.SubGrupoDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:5173","http://localhost:4200"}, allowCredentials = "false")
@RestController
@RequestMapping("/api/cadastros/subgrupos")
public class SubGrupoController {

    private final SubGrupoService service;

    public SubGrupoController(SubGrupoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<SubGrupoDto>> list(
            @RequestParam(value = "nomeSubGrupo", required = false) String nomeSubGrupo,
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina
    ) {
        return ResponseEntity.ok(service.list(nomeSubGrupo, pagina, tamanhoPagina));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubGrupoDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<SubGrupoDto> create(@Valid @RequestBody SubGrupoDto body) {
        return ResponseEntity.ok(service.create(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubGrupoDto> update(@PathVariable Integer id, @Valid @RequestBody SubGrupoDto body) {
        return ResponseEntity.ok(service.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
