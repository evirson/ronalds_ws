package br.com.vetorsistemas.ronalds_ws.cadastro.veiculo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para representação de imagens de veículos
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Dados de uma imagem de veículo")
public class VeiculoImagemDto {

    @Schema(description = "ID da imagem", example = "1")
    private Integer id;

    @Schema(description = "Código do veículo associado", example = "1")
    private Integer codVei;

    @Schema(description = "Nome do arquivo", example = "foto-veiculo-frente.jpg")
    private String nomeArq;

    @Schema(description = "Tipo de conteúdo (MIME type)", example = "image/jpeg")
    private String contentType;

    @Schema(description = "Tamanho do arquivo em bytes", example = "524288")
    private Long tamanho;

    @Schema(description = "URL para visualização da imagem", example = "/api/veiculos/imagens/1/preview")
    private String urlPreview;

    @Schema(description = "URL para download da imagem", example = "/api/veiculos/imagens/1")
    private String urlDownload;
}
