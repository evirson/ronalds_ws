package br.com.vetorsistemas.ronalds_ws.movimento.detalhesCaixa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalheCaixaDto {
    private Integer id;

    @NotBlank
    @Size(max = 8)
    private String planoContas;

    @Size(max = 15)
    private String numeroDocumento;

    @Size(max = 50)
    private String historico;

    @NotNull
    private LocalDateTime dataMovimento;

    @NotNull
    private Double valorMovimento;

    @NotBlank
    @Pattern(regexp = "[DC]")
    private String debitoCredito;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer codigoCaixa;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String status;
}
