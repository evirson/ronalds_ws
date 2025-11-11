package br.com.vetorsistemas.ronalds_ws.cadastro.classe.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClasseDto {

    private Integer id;
    private String nomeClasse;
    @Pattern(regexp = "1|2|3", message = "Classe 1-Produto / 2-Fornecedr / 3-Cliente")
    private String tipoClasse;  //1- Produto 2 - Fornecedor 3- Clientes
    private Double percentualDescontoPadrao;

}
