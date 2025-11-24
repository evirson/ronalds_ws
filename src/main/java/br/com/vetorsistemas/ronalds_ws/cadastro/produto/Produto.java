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
    @Column(name = "TIPPRO", nullable = false, length = 1)    //1-Original 2-Servico 3-Terceiros 4-Geometria 5-Lubrificantes 6-Produtos Alternativos 7-Peças sem referência
    private String tipoProduto; //verificar os tipos diversos
    @Column(name = "NOMPRO", nullable = false, length = 80)
    private String descricao;
    @Column(name = "REFPRO", nullable = false, length = 25)
    private String referencia;
    @Column(name = "CODFOR", nullable = false)  //vem do CADFOR fornecedores
    private Integer idFornecedor;
    @Column(name = "CODFAB", nullable = false)  //vem do CADFAB fabrica
    private Integer idFabricante;
    @Column(name = "CODCLA", nullable = false)  //vem do CADCLA classe
    private Integer idClasse;
    @Column(name = "CODGRU", nullable = false)  //vem do CADGRU grupo
    private Integer idGrupo;
    @Column(name = "CODSUB", nullable = false)  //vem do CADSUB subGrupo
    private Integer idSubGrupo;
    @Column(name = "UNIPRO", length = 2, nullable = false)  //Unidade do Produto
    private String unidade;
    @Column(name = "CUSREA")  //Custo Reais
    private Double custo;
    @Column(name = "PERLUC")
    private Double percentualLucro;
    @Column(name = "PREAVI")  //preco a vista
    private Double precoVista;
    @Column(name = "PRAGAR")
    private Integer prazoGarantiaMeses;
    @Column(name = "PERICM")
    private Double percentualIcm;  //99.99  NN
    @Column(name = "PERIPI")
    private Double percentualIpi;
    @Column(name = "PERBAS")
    private Double percentualBaseCalculo;
    @Column(name = "TABPRE", columnDefinition = "0")
    private Integer tabelaPreco;  //0 - sem tabela
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
    @Column(name = "DATCAD")
    private LocalDateTime dataCadastro;
    @Column(name = "DATATU")
    private LocalDateTime dataAtualizacao;

}
