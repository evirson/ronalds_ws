package br.com.vetorsistemas.ronalds_ws.cadastro.classe;

import br.com.vetorsistemas.ronalds_ws.cadastro.classe.dto.ClasseDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/classes")
public class ClasseController {

    private final ClasseService service;

    public ClasseController(ClasseService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<ClasseDto>> list(
            @RequestParam(value = "tipoClasse", required = true) String tipoClasse,
            @RequestParam(value = "nomeClasse", required = false) String nomeClasse,
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina
    ) {
        return ResponseEntity.ok(service.search(tipoClasse, nomeClasse, pagina, tamanhoPagina));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClasseDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ClasseDto> create(@Valid @RequestBody ClasseDto body) {
        return ResponseEntity.ok(service.create(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClasseDto> update(@PathVariable Integer id,
                                                @Valid @RequestBody ClasseDto body) {
        return ResponseEntity.ok(service.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
