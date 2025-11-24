package br.com.vetorsistemas.ronalds_ws.cadastro.pais.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaisCreateUpdateDTO {
    @NotBlank
    @Size(max = 100)
    private String nome;
}
