package br.com.vetorsistemas.ronalds_ws.cadastro.empresa;

import br.com.vetorsistemas.ronalds_ws.cadastro.empresa.dto.EmpresaDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = {"http://localhost:3000","http://localhost:5173","http://localhost:4200"}, allowCredentials = "false")
@RestController
@RequestMapping("/api/cadastros/empresas")
public class EmpresaController {

    private final EmpresaService service;

    public EmpresaController(EmpresaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<EmpresaDTO>> list(
            @RequestParam(value = "razaoSocial", required = false) String razaoSocial,
            @RequestParam(value = "cpfCnpj", required = false) String cpfCnpj,
            @RequestParam(value = "cidade", required = false) String cidade,
            @RequestParam(value = "estado", required = false) String estado,
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina
    ) {
        return ResponseEntity.ok(service.list(razaoSocial, cpfCnpj, cidade, estado, pagina, tamanhoPagina));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<EmpresaDTO> create(@Valid @RequestBody EmpresaDTO body) {
        return ResponseEntity.ok(service.create(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDTO> update(@PathVariable Integer id, @Valid @RequestBody EmpresaDTO body) {
        return ResponseEntity.ok(service.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
