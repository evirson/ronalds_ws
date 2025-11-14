package br.com.vetorsistemas.ronalds_ws.cadastro.pais.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaisDTO {
    private Integer id;
    private String nome;
}
