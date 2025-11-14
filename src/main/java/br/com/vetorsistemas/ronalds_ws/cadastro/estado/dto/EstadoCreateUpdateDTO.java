package br.com.vetorsistemas.ronalds_ws.cadastro.estado.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstadoCreateUpdateDTO {
    private String sigla;
    private String nome;
    private Integer codigoPais;
}
