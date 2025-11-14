package br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.itemOrdemServico;

import br.com.vetorsistemas.ronalds_ws.cadastro.produto.Produto;
import br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.ordemServico.OrdemServico;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "SRVITN")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemOrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gt_srvitn_coditn")
    @SequenceGenerator(name = "gt_srvitn_coditn", sequenceName = "gt_srvitn_coditn", allocationSize = 1)
    @Column(name = "CODITN", nullable = false)
    private Integer id;

    @Column(name = "CODSRV", nullable = false)
    private Integer codigoOrdemServico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CODSRV", insertable = false, updatable = false)
    private OrdemServico ordemServico;

    @Column(name = "CODPRO", nullable = false)
    private Integer codigoProduto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CODPRO", insertable = false, updatable = false)
    private Produto produto;

    @Column(name = "TIPPRO", length = 1, nullable = false)
    private String tipoProduto;

    @Column(name = "DESPRO", length = 50, nullable = false)
    private String descricaoProduto;

    @Column(name = "QTDPRO")
    private Double quantidade;

    @Column(name = "PREUNI")
    private Double precoUnitario;

    @Column(name = "PERDES")
    private Double percentualDesconto;

    @Column(name = "VALDES")
    private Double valorDesconto;

    @Column(name = "VALLIQ")
    private Double valorLiquido;

    @Column(name = "VALBRU")
    private Double valorBruto;

    @Column(name = "CODCFO")
    private Integer codigoCfo;
}
