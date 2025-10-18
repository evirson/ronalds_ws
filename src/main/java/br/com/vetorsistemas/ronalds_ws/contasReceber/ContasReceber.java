package br.com.vetorsistemas.ronalds_ws.contasReceber;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "CADREC")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ContasReceber {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gt_cadrec_codrec")
    @SequenceGenerator(
            name = "gt_cadrec_codrec",
            sequenceName = "gt_cadrec_codrec", // nome real da sequência no banco
            allocationSize = 1                     // incrementa de 1 em 1
    )
    @Column(name = "CODREC")
    private Integer id;
    @Column(name = "CODSRV", nullable = false)
    private Integer codigoServico;
    @Column(name = "CODCPV")
    private Integer idCondicaoPagVenda;
    @Column(name = "DATMOV", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime dataMovimento;
    @Column(name = "DESREC", length = 50, nullable = false)
    private String descricao;
    @Column(name = "CODCLI", nullable = false)
    private Integer idCliente;
    @Column(name = "NUMDOC", length = 20)
    private String documento;
    @Column(name = "DATVCT", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime dataVencimenot;
    @Column(name = "PREVIS", length = 1)
    private String previs;  //S - N
    @Column(name = "VALREC")
    private Double ValorReceber;
    @Column(name = "VALREC")
    private Double ValorIndice;
    @Column(name = "OBSERV", length = 255)  //original é 500
    private String observacoes;
    @Column(name = "VALJUR")
    private Double valorJuros;
    @Column(name = "VALMUL")
    private Double valorMulta;
    @Column(name = "VALDES")
    private Double valorDesconto;
    @Column(name = "DATRCT", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime dataRecebimento;
    @Column(name = "VALRET")
    private Double valorRecebido;
    @Column(name = "CODPLA", length = 8)
    private String planoContas;

}
