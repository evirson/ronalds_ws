package br.com.vetorsistemas.ronalds_ws.cadastro.estado.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstadoCreateUpdateDTO {
    @Size(max = 2)
    private String sigla;
    @Size(max = 30)
    private String nome;
    private Integer codigoPais;
}
