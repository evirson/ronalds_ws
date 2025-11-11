package br.com.vetorsistemas.ronalds_ws.cadastro.grupo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GrupoDto {
    private Integer id;
    @NotBlank
    @Size(max = 50)
    private String nomeGrupo;
}
