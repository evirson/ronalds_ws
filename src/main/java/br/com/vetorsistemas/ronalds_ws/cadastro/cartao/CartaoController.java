package br.com.vetorsistemas.ronalds_ws.cadastro.cartao;

import br.com.vetorsistemas.ronalds_ws.cadastro.cartao.Dto.CartaoDto;
import jakarta.validation.Valid;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cartao-credito")
public class CartaoController {

    private final CartaoService service;

    public CartaoController(CartaoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CartaoDto>> list(@RequestParam(value = "descricao", required = false) String descricao) {
        return ResponseEntity.ok(service.findAll(descricao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartaoDto> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<CartaoDto> create(@Valid @RequestBody CartaoDto body) {
        CartaoDto created = service.create(body);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartaoDto> update(@PathVariable Integer id, @Valid @RequestBody CartaoDto body) {
        return ResponseEntity.ok(service.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
