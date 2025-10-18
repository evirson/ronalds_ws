package br.com.vetorsistemas.ronalds_ws.fornecedor;

import br.com.vetorsistemas.ronalds_ws.fornecedor.dto.FornecedorCreateUpdateDTO;
import br.com.vetorsistemas.ronalds_ws.fornecedor.dto.FornecedorDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController {

    private final FornecedorService service;

    public FornecedorController(FornecedorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<FornecedorDTO>> list(
            @RequestParam(value = "razaoSocial", required = false) String razaoSocial,
            @RequestParam(value = "nomeFantasia", required = false) String nomeFantasia,
            @RequestParam(value = "cnpj", required = false) String cnpj,
            @RequestParam(value = "telefone", required = false) String telefone,
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina
    ) {
        return ResponseEntity.ok(service.search(razaoSocial, nomeFantasia,
                cnpj, telefone, pagina, tamanhoPagina));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FornecedorDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<FornecedorDTO> create(@Valid @RequestBody FornecedorCreateUpdateDTO body) {
        return ResponseEntity.ok(service.create(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FornecedorDTO> update(@PathVariable Integer id,
                                                @Valid @RequestBody FornecedorCreateUpdateDTO body) {
        return ResponseEntity.ok(service.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
