package br.com.vetorsistemas.ronalds_ws.cadastro.veiculo;

import br.com.vetorsistemas.ronalds_ws.cadastro.veiculo.dto.VeiculoImagemDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Controller responsável pelo gerenciamento de imagens de veículos
 */
@RestController
@RequestMapping("/api/veiculos")
@RequiredArgsConstructor
@Tag(name = "Imagens de Veículos", description = "Gerenciamento de imagens associadas aos veículos")
@SecurityRequirement(name = "bearer-key")
public class VeiculoImagemController {

    private final VeiculoImagemService service;

    @PostMapping("/{codVei}/imagens")
    @Operation(
        summary = "Upload de imagens do veículo",
        description = "Faz upload de uma ou mais imagens para um veículo específico. " +
                      "Aceita apenas arquivos de imagem (JPEG, PNG, GIF, WEBP) com tamanho máximo de 5MB cada."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Imagens enviadas com sucesso",
                     content = @Content(schema = @Schema(implementation = VeiculoImagemDto.class))),
        @ApiResponse(responseCode = "400", description = "Arquivo inválido (tipo ou tamanho)"),
        @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    })
    public ResponseEntity<List<VeiculoImagemDto>> uploadImagens(
            @Parameter(description = "Código do veículo", required = true, example = "1")
            @PathVariable Integer codVei,
            @Parameter(description = "Lista de arquivos de imagem", required = true)
            @RequestParam("imagens") List<MultipartFile> imagens) {
        List<VeiculoImagemDto> imagensSalvas = service.salvarImagens(codVei, imagens);
        return ResponseEntity.status(HttpStatus.CREATED).body(imagensSalvas);
    }

    @GetMapping("/{codVei}/imagens")
    @Operation(
        summary = "Listar imagens do veículo",
        description = "Retorna a lista de todas as imagens associadas a um veículo específico, " +
                      "incluindo ID, nome do arquivo e tamanho em bytes."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de imagens retornada com sucesso",
                     content = @Content(schema = @Schema(implementation = VeiculoImagemDto.class))),
        @ApiResponse(responseCode = "404", description = "Veículo não encontrado")
    })
    public ResponseEntity<List<VeiculoImagemDto>> listarImagens(
            @Parameter(description = "Código do veículo", required = true, example = "1")
            @PathVariable Integer codVei) {
        return ResponseEntity.ok(service.listarImagensPorVeiculo(codVei));
    }

    @GetMapping("/imagens/{id}")
    @Operation(
        summary = "Download de imagem",
        description = "Faz o download de uma imagem específica pelo seu ID. O arquivo será retornado com o nome original."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Imagem retornada com sucesso",
                     content = @Content(mediaType = "application/octet-stream")),
        @ApiResponse(responseCode = "404", description = "Imagem não encontrada")
    })
    public ResponseEntity<byte[]> downloadImagem(
            @Parameter(description = "ID da imagem", required = true, example = "1")
            @PathVariable Integer id) {
        VeiculoImagem imagem = service.buscarImagemPorId(id);

        HttpHeaders headers = new HttpHeaders();

        // Define o tipo de conteúdo baseado no que foi armazenado
        if (imagem.getContentType() != null) {
            headers.setContentType(MediaType.parseMediaType(imagem.getContentType()));
        } else {
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        }

        headers.setContentDispositionFormData("attachment", imagem.getNomeArq());
        headers.setContentLength(imagem.getDocumento().length);

        return ResponseEntity.ok()
                .headers(headers)
                .body(imagem.getDocumento());
    }

    @GetMapping("/imagens/{id}/preview")
    @Operation(
        summary = "Visualizar imagem",
        description = "Retorna a imagem para visualização inline no navegador."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Imagem retornada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Imagem não encontrada")
    })
    public ResponseEntity<byte[]> visualizarImagem(
            @Parameter(description = "ID da imagem", required = true, example = "1")
            @PathVariable Integer id) {
        VeiculoImagem imagem = service.buscarImagemPorId(id);

        HttpHeaders headers = new HttpHeaders();

        if (imagem.getContentType() != null) {
            headers.setContentType(MediaType.parseMediaType(imagem.getContentType()));
        } else {
            headers.setContentType(MediaType.IMAGE_JPEG);
        }

        headers.setContentLength(imagem.getDocumento().length);

        return ResponseEntity.ok()
                .headers(headers)
                .body(imagem.getDocumento());
    }

    @DeleteMapping("/imagens/{id}")
    @Operation(
        summary = "Excluir imagem",
        description = "Exclui uma imagem específica pelo seu ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Imagem excluída com sucesso"),
        @ApiResponse(responseCode = "404", description = "Imagem não encontrada")
    })
    public ResponseEntity<Void> excluirImagem(
            @Parameter(description = "ID da imagem", required = true, example = "1")
            @PathVariable Integer id) {
        service.excluirImagem(id);
        return ResponseEntity.noContent().build();
    }
}
