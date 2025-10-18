package br.com.vetorsistemas.ronalds_ws.cartao.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CartaoDto {

    private String descricao;
    private Double taxaAdministrativa;
    private Integer diaFechamento;
    private Integer diaCredito;

}
