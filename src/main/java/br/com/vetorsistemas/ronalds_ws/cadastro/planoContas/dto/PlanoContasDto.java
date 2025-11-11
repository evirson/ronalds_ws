package br.com.vetorsistemas.ronalds_ws.cadastro.planoContas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanoContasDto {
    @NotBlank @Size(max = 8)
    private String id;
    @NotBlank @Size(max = 50)
    private String descricao;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAlteracao;
}
