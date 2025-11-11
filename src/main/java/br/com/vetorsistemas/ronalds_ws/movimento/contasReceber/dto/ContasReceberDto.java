package br.com.vetorsistemas.ronalds_ws.movimento.contasReceber.dto;

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
public class ContasReceberDto {
    private Integer id;
    private Integer codigoServico;
    private Integer idCondicaoPagVenda;
    @NotNull
    private LocalDateTime dataMovimento;
    @NotBlank
    private String descricao;
    @NotNull
    private Integer idCliente;
    private String documento;
    @NotNull
    private LocalDateTime dataVencimenot;
    private String previs;
    private Double valorReceber;
    private Double valorIndice;
    private String observacoes;
    private Double valorJuros;
    private Double valorMulta;
    private Double valorDesconto;
    private LocalDateTime dataRecebimento;
    private Double valorRecebido;
    private String planoContas;
}
