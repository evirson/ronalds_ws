package br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.itemOrdemServico;

import br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.itemOrdemServico.dto.ItemOrdemServicoCreateUpdateDTO;
import br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.itemOrdemServico.dto.ItemOrdemServicoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimentos/itens-ordem-servico")
@Tag(name = "Itens da Ordem de Serviço", description = "Endpoints para gerenciamento de Itens de Ordens de Serviço")
public class ItemOrdemServicoController {

    private final ItemOrdemServicoService service;

    public ItemOrdemServicoController(ItemOrdemServicoService service) {
        this.service = service;
    }

    @GetMapping
    @CrossOrigin
    public ResponseEntity<Page<ItemOrdemServicoDTO>> list(
            @RequestParam(value = "codigoOrdemServico", required = false) Integer codigoOrdemServico,
            @RequestParam(value = "codigoProduto", required = false) Integer codigoProduto,
            @RequestParam(value = "tipoProduto", required = false) String tipoProduto,
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina
    ) {
        return ResponseEntity.ok(service.search(codigoOrdemServico, codigoProduto, tipoProduto, pagina, tamanhoPagina));
    }

    @GetMapping("/ordem-servico/{codigoOrdemServico}")
    @CrossOrigin
    public ResponseEntity<List<ItemOrdemServicoDTO>> listByOrdemServico(@PathVariable Integer codigoOrdemServico) {
        return ResponseEntity.ok(service.findByOrdemServico(codigoOrdemServico));
    }

    @GetMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<ItemOrdemServicoDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @CrossOrigin
    public ResponseEntity<ItemOrdemServicoDTO> create(@Valid @RequestBody ItemOrdemServicoCreateUpdateDTO body) {
        return ResponseEntity.ok(service.create(body));
    }

    @PutMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<ItemOrdemServicoDTO> update(@PathVariable Integer id,
                                                       @Valid @RequestBody ItemOrdemServicoCreateUpdateDTO body) {
        return ResponseEntity.ok(service.update(id, body));
    }

    @DeleteMapping("/{id}")
    @CrossOrigin
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
