package br.com.vetorsistemas.ronalds_ws.cadastro.estado.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstadoDTO {
    private Integer id;
    private String sigla;
    private String nome;
    private Integer codigoPais;
    private String nomePais;
}
