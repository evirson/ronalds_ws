package br.com.vetorsistemas.ronalds_ws.cadastro.produto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "CADPRO")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gt_cadpro_codpro")
    @SequenceGenerator(
            name = "gt_cadpro_codpro",
            sequenceName = "gt_cadpro_codpro", // nome real da sequência no banco
            allocationSize = 1                     // incrementa de 1 em 1
    )
    @Column(name = "CODPRO", nullable = false)
    private Integer id;

    @Column(name = "TIPPRO", nullable = false, length = 1)
    private String tipoProduto; //verificar os tipos diversos
    @Column(name = "CODFAB", nullable = false)
    private Integer idFabricante;
    @Column(name = "CODCLA", nullable = false)
    private Integer idClasse;
    @Column(name = "CODGRU", nullable = false)
    private Integer idGrupo;
    @Column(name = "CODSUB", nullable = false)
    private Integer idSubGrupo;
    @Column(name = "UNIPRO", length = 2, nullable = false)
    private String unidade;
    @Column(name = "CUSREA")
            private Double custo;
    @Column(name = "CUSIND")
            private Double custoIndice;
    @Column(name = "PREAVI")
            private Double precoVista;
    @Column(name = "DSCAVI")
    private Double descontoVista;
    @Column(name = "AVIATV", length = 1)
            private String ativaPrecoVista;
    @Column(name = "AVIINI")
    @JsonFormat(pattern = "dd-MM-yyyy")
            private LocalDateTime inicioUsoPrecoVista;
    @Column(name = "AVIFIM")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime finalUsoPrecoVista;
    @Column(name = "PREPRA")
            private Double precoPrazo;
    @Column(name = "DSCPRA")
    private Double descontoPrazo;
    @Column(name = "PRAATV", length = 1)
    private String ativaPrecoPrazo;
    @Column(name = "PRAINI")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime inicioUsoPrecoPrazo;
    @Column(name = "PRAFIM")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime finalUsoPrecoPrazo;
    @Column(name = "PRAGAR")
    private Integer prazoGarantiaMeses;
    @Column(name = "BXAEST", length = 1)
    private String baixaEstoque;
    @Column(name = "CRVABC")
            private String curvaAbc;  //envia A/B/C
    @Column(name = "PERLUC")
            private Double percentualLucro;
    @Column(name = "CUSMED")
            private Double custoMedio;
    @Column(name = "ULTCPR")
            private LocalDateTime ultimaCompra;
    @Column(name = "ULTVDA")
    private LocalDateTime ultimaVenda;
    @Column(name = "CLAORI", length = 1)
    private String classificacaoOrigem;
    @Column(name = "CLATRI", length = 1)
    private String classificacaoTributaria;
    @Column(name = "PERICM")
    private Double percentualIcm;  //99.99  NN
    @Column(name = "PERIPI")
            private Double percentualIpi;
    @Column(name = "PERBAS")
    private Double percentualBaseCalculo;
    @Column(name = "CLAFIS", length = 4)
            private String classificaoFiscal;
    @Column(name = "TABPRE")
            private Integer tabelaPreco;  //0 - sem tabela
    @Column(name = "DATCAD")
            private LocalDateTime dataCadastro;
    @Column(name = "DATATU")
    private LocalDateTime dataAtualizacao;
    @Column(name = "NOMPRO", nullable = false, length = 80)
    private String descricao;
    @Column(name = "REFPRO", nullable = false, length = 25)
    private String referencia;
    @Column(name = "PERDEF")
    private Double percentualDescontoPadrao;
    @Column(name = "QTDDEF")
    private Double quantidadePadrao;
    @Column(name = "OBSPRO")
    private String observacoes;
    @Column(name = "CODNCM", length = 20)
            private String ncm;
    @Column(name = "SITTRI")
    private Integer situacaoTributaria;
    @Column(name = "CODCFO")
    private Integer codigoCfo;
    @Column(name = "C_CEST", length = 7)
            private String cCest;
    @Column(name = "CSTPIS")
            private Integer cstPis;
    @Column(name = "PERPIS")
    private Double percentualPis;
    @Column(name = "CSTCOF")
    private Integer cstCofins;
    @Column(name = "PERCOF")
    private Double percentualCofins;


}
