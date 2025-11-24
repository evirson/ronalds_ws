package br.com.vetorsistemas.ronalds_ws.cadastro.municipio;

import br.com.vetorsistemas.ronalds_ws.cadastro.municipio.dto.MunicipioDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cadastros/municipios")
public class MunicipioController {

    private final MunicipioService service;

    public MunicipioController(MunicipioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<MunicipioDto>> list(
            @RequestParam(value = "codigoEstado", required = false) Integer codigoEstado,
            @RequestParam(value = "municipio", required = false) Integer municipio,
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina
    ) {
        return ResponseEntity.ok(service.list(codigoEstado, municipio, pagina, tamanhoPagina));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MunicipioDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<MunicipioDto> create(@Valid @RequestBody MunicipioDto body) {
        return ResponseEntity.ok(service.create(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MunicipioDto> update(@PathVariable Integer id, @Valid @RequestBody MunicipioDto body) {
        return ResponseEntity.ok(service.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
