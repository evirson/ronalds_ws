package br.com.vetorsistemas.ronalds_ws.movimento.contaCorrente.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContaCorrenteDto {
    private Integer id;
    private Integer banco;
    private Integer agencia;
    private Integer conta;
    @Size(max = 40)
    private String descricao;
    private LocalDateTime dataAbertura;
    private String tipoConta;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAlteracao;
}
