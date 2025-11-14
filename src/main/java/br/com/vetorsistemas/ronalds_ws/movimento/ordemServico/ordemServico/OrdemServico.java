package br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.ordemServico;

import br.com.vetorsistemas.ronalds_ws.cadastro.cliente.Cliente;
import br.com.vetorsistemas.ronalds_ws.cadastro.funcionario.Funcionario;
import br.com.vetorsistemas.ronalds_ws.cadastro.veiculo.Veiculo;
import br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.itemOrdemServico.ItemOrdemServico;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "CADSRV")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gt_cadsrv_codsrv")
    @SequenceGenerator(name = "gt_cadsrv_codsrv", sequenceName = "gt_cadsrv_codsrv", allocationSize = 1)
    @Column(name = "CODSRV", nullable = false)
    private Integer id;

    @Column(name = "TIPSRV", length = 1, nullable = false)
    private String tipoServico;

    @Column(name = "DATEMI", nullable = false)
    private LocalDateTime dataAbertura;

    @Column(name = "HOREMI", nullable = false)
    private LocalDateTime horaAbertura;

    @Column(name = "RESPON", length = 25)
    private String responsavel;

    @Column(name = "CODFUN", nullable = false)
    private Integer codigoFuncionario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CODFUN", insertable = false, updatable = false)
    private Funcionario funcionario;

    @Column(name = "CODCLI", nullable = false)
    private Integer codigoCliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CODCLI", insertable = false, updatable = false)
    private Cliente cliente;

    @Column(name = "CODVEI", nullable = false)
    private Integer codigoVeiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CODVEI", insertable = false, updatable = false)
    private Veiculo veiculo;

    @Column(name = "CODCPV", nullable = false)
    private Integer codigoCondicaoPagamento;

    @Column(name = "HORFIM")
    private LocalDateTime horaFinalizacao;

    @Column(name = "DATFIM")
    private LocalDateTime dataFinalizacao;

    @Column(name = "GARPEC")
    private Integer garantiaPecasMeses;

    @Column(name = "GARMOB")
    private Integer garantiaMaoObraMeses;

    @Column(name = "LIBSRV", length = 1)
    private String servicoLiberado;

    @Column(name = "TOTBRU")
    private Double totalBruto;

    @Column(name = "VALDES")
    private Double valorDesconto;

    @Column(name = "TOTLIQ")
    private Double totalLiquido;

    @Column(name = "FATSRV", length = 1)
    private String faturamento;

    @Column(name = "DATFAT")
    private LocalDateTime dataFaturamento;

    @Column(name = "NUMNFS", length = 10)
    private String numeroNotaFiscalServico;

    @Column(name = "NUMNFV", length = 10)
    private String numeroNotaFiscalProduto;

    @Column(name = "OBSSRV", length = 500)
    private String observacoes;

    @Column(name = "CODGEO")
    private Integer codigoGeometrista;

    @Column(name = "PROBRU", precision = 9, scale = 2)
    private BigDecimal produtoBruto;

    @Column(name = "PRODES", precision = 9, scale = 2)
    private BigDecimal produtoDesconto;

    @Column(name = "PROLIQ", precision = 9, scale = 2)
    private BigDecimal produtoLiquido;

    @Column(name = "SERBRU", precision = 9, scale = 2)
    private BigDecimal servicoBruto;

    @Column(name = "SERDES", precision = 9, scale = 2)
    private BigDecimal servicoDesconto;

    @Column(name = "SERLIQ", precision = 9, scale = 2)
    private BigDecimal servicoLiquido;

    @Column(name = "LUBBRU", precision = 9, scale = 2)
    private BigDecimal lubrificanteBruto;

    @Column(name = "LUBDES", precision = 9, scale = 2)
    private BigDecimal lubrificanteDesconto;

    @Column(name = "LUBLIQ", precision = 9, scale = 2)
    private BigDecimal lubrificanteLiquido;

    @Column(name = "CODSMV", length = 50)
    private String codigoSmv;

    @Column(name = "CODEMP", length = 50)
    private String codigoEmpenho;

    @Column(name = "CODORC", length = 50)
    private String codigoOrcamento;

    @Column(name = "PARBRU", precision = 9, scale = 2)
    private BigDecimal pecaParalelaBruta;

    @Column(name = "PARDES", precision = 9, scale = 2)
    private BigDecimal pecaParalelaDesconto;

    @Column(name = "PARLIQ", precision = 9, scale = 2)
    private BigDecimal pecaParalelaLiquida;

    @Column(name = "TOTDESLOC")
    private Double totalDeslocamento;

    @OneToMany(mappedBy = "ordemServico", fetch = FetchType.LAZY)
    private List<ItemOrdemServico> itens;
}
