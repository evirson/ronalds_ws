package br.com.vetorsistemas.ronalds_ws.classe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ClasseDto {

    private String nomeClasse;
    private String tipoClasse;
    private Double percentualDescontoPadrao;

}
