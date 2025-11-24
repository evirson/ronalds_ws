package br.com.vetorsistemas.ronalds_ws.cadastro.cliente;

import br.com.vetorsistemas.ronalds_ws.cadastro.cliente.dto.ClienteCreateUpdateDTO;
import br.com.vetorsistemas.ronalds_ws.cadastro.cliente.dto.ClienteDTO;
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
@RequestMapping("/api/cadastros/clientes")
@Tag(name = "Clientes", description = "Endpoints para gerenciamento de Clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar Clientes", description = "Lista todos os clientes com filtros opcionais")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    public ResponseEntity<Page<ClienteDTO>> list(
            @RequestParam(value = "cnpjCpf", required = false) String cnpcpf,
            @RequestParam(value = "nomeRazaoSocial", required = false) String raznom,
            @RequestParam(value = "nomeFantasia", required = false) String fannat,
            @RequestParam(value = "telefone", required = false) String telcli,
            @RequestParam(value = "celular", required = false) String celcli,
            @RequestParam(value = "email", required = false) String maicli,
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina
    ) {
        return ResponseEntity.ok(service.search(cnpcpf, raznom, fannat, telcli, celcli, maicli, pagina, tamanhoPagina));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Cliente por ID", description = "Retorna um cliente específico pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    public ResponseEntity<ClienteDTO> getById(
            @Parameter(description = "ID do Cliente") @PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @Operation(summary = "Criar Cliente", description = "Cria um novo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteCreateUpdateDTO body) {
        return ResponseEntity.ok(service.create(body));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Cliente", description = "Atualiza um cliente existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<ClienteDTO> update(
            @Parameter(description = "ID do Cliente") @PathVariable Integer id,
            @Valid @RequestBody ClienteCreateUpdateDTO body) {
        return ResponseEntity.ok(service.update(id, body));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir Cliente", description = "Exclui um cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado")
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID do Cliente") @PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
