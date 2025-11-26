package br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.ordemServico;

import br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.ordemServico.dto.OrdemServicoCreateUpdateDTO;
import br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.ordemServico.dto.OrdemServicoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/ordem-servico")
@Tag(name = "Ordem de Serviço", description = "Endpoints para gerenciamento de Ordens de Serviço")
public class OrdemServicoController {

    private final OrdemServicoService service;

    public OrdemServicoController(OrdemServicoService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "Listar Ordens de Serviço", description = "Lista todas as Ordens de Serviço com filtros opcionais")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    public ResponseEntity<Page<OrdemServicoDTO>> list(
            @RequestParam(value = "codigoOS", required = false) Integer codigoOS,
            @RequestParam(value = "placa", required = false) String placa,
            @RequestParam(value = "dataInicial", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dataInicial,
            @RequestParam(value = "dataFinal", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate dataFinal,
            @RequestParam(value = "codigoFuncionario", required = false) Integer codigoFuncionario,
            @RequestParam(value = "codigoCliente", required = false) Integer codigoCliente,
            @RequestParam(value = "numeroNotaFiscalServico", required = false) String numeroNotaFiscalServico,
            @RequestParam(value = "numeroNotaFiscalProduto", required = false) String numeroNotaFiscalProduto,
            @RequestParam(value = "faturamento", required = false) String faturamento,
            @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(value = "tamanho-pagina", defaultValue = "10") Integer tamanhoPagina
    ) {
        return ResponseEntity.ok(service.search(
                codigoOS,
                placa,
                dataInicial,
                dataFinal,
                codigoFuncionario,
                codigoCliente,
                numeroNotaFiscalServico,
                numeroNotaFiscalProduto,
                faturamento,
                pagina,
                tamanhoPagina
        ));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Ordem de Serviço por ID", description = "Retorna uma Ordem de Serviço específica pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ordem de Serviço encontrada"),
            @ApiResponse(responseCode = "404", description = "Ordem de Serviço não encontrada")
    })
    public ResponseEntity<OrdemServicoDTO> getById(
            @Parameter(description = "ID da Ordem de Serviço") @PathVariable Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @Operation(summary = "Criar Ordem de Serviço", description = "Cria uma nova Ordem de Serviço")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ordem de Serviço criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<OrdemServicoDTO> create(@Valid @RequestBody OrdemServicoCreateUpdateDTO body) {
        return ResponseEntity.ok(service.create(body));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Ordem de Serviço", description = "Atualiza uma Ordem de Serviço existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ordem de Serviço atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ordem de Serviço não encontrada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<OrdemServicoDTO> update(
            @Parameter(description = "ID da Ordem de Serviço") @PathVariable Integer id,
            @Valid @RequestBody OrdemServicoCreateUpdateDTO body) {
        return ResponseEntity.ok(service.update(id, body));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir Ordem de Serviço", description = "Exclui uma Ordem de Serviço (não permite se estiver faturada)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ordem de Serviço excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ordem de Serviço não encontrada"),
            @ApiResponse(responseCode = "400", description = "Ordem de Serviço está faturada e não pode ser excluída")
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID da Ordem de Serviço") @PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
