package br.com.vetorsistemas.ronalds_ws.movimento.contasPagar;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "CADPAG")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ContasPagar {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gt_cadpag_codpag")
    @SequenceGenerator(
            name = "gt_cadpag_codpag",
            sequenceName = "gt_cadpag_codpag", // nome real da sequência no banco
            allocationSize = 1                     // incrementa de 1 em 1
    )
    @Column(name = "CODPAG")
    private Integer id;
    @Column(name = "CODPED")
    private Integer codigoPedido;
    @Column(name = "DATMOV", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime dataMovimento;
    @Column(name = "CODPLA", length = 8)
    private String planoContas;
    @Column(name = "CODFOR")
    private Integer idFornecedor;
    @Column(name = "NUMDOC", length = 20)
    private String documento;
    @Column(name = "COMDUP", length = 1)
    private String comDuplicata;  //S - N
    @Column(name = "DATVCT", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime dataVencimento;
    @Column(name = "PERMUL")
            private Double percentualMulta;
    @Column(name = "PERJUR")
    private Double percentualJuros;
    @Column(name = "VALVCT")
    private Double valorVencimento;
    @Column(name = "DESVCT")
    private Double descontoVencimento;
    @Column(name = "VLDDES", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime validadeDesconto;
    @Column(name = "VALPAG")
    private Double valorPago;
    @Column(name = "OBSPAG", length = 255)  //original é 500
    private String observacoes;
    @Column(name = "DATPAG", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime dataPagamento;
    @Column(name = "DESPAG", length = 50)  //original é 500
    private String descricao;

}
