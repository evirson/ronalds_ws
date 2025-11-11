package br.com.vetorsistemas.ronalds_ws.cadastro.subGrupo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubGrupoDto {
    private Integer id;
    @NotBlank
    @Size(max = 50)
    private String nomeSubGrupo;
}
