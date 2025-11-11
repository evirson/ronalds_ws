package br.com.vetorsistemas.ronalds_ws.movimento.contasPagar.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ContasPagarDto {

    private Integer id;
    private Integer codigoPedido;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime dataMovimento;
    private String planoContas;
    private Integer idFornecedor;
    private String documento;
    private String comDuplicata;  //S - N
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime dataVencimento;
    private Double percentualMulta;
    private Double percentualJuros;
    private Double valorVencimento;
    private Double descontoVencimento;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime validadeDesconto;
    private Double valorPago;
    private String observacoes;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime dataPagamento;
    private String descricao;

}
