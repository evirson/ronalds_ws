package br.com.vetorsistemas.ronalds_ws.cadastro.cliente;

import br.com.vetorsistemas.ronalds_ws.cadastro.cliente.dto.ClienteCreateUpdateDTO;
import br.com.vetorsistemas.ronalds_ws.cadastro.cliente.dto.ClienteDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cadastros/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<ClienteDTO>> list(
            @RequestParam(value = "cnpcpf", required = false) String cnpcpf,
            @RequestParam(value = "raznom", required = false) String raznom,
            @RequestParam(value = "fannat", required = false) String fannat,
            @RequestParam(value = "telcli", required = false) String telcli,
            @RequestParam(value = "celcli", required = false) String celcli,
            @RequestParam(value = "maicli", required = false) String maicli,
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina
    ) {
        return ResponseEntity.ok(service.search(cnpcpf, raznom, fannat, telcli, celcli, maicli, pagina, tamanhoPagina));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteCreateUpdateDTO body) {
        return ResponseEntity.ok(service.create(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id,
                                             @Valid @RequestBody ClienteCreateUpdateDTO body) {
        return ResponseEntity.ok(service.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
