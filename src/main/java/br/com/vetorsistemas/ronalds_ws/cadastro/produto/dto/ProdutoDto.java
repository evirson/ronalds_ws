package br.com.vetorsistemas.ronalds_ws.cadastro.produto.dto;

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
public class ProdutoDto {
    private Integer id;
    private String tipoProduto;
    private Integer idFornecedor;
    private Integer idFabricante;
    private Integer idClasse;
    private Integer idGrupo;
    private Integer idSubGrupo;
    @Size(max = 2)
    private String unidade;
    private Double custo;
    private Double precoVista;
    private Integer prazoGarantiaMeses;
    private Double percentualLucro;
    private Double percentualIcm;
    private Double percentualIpi;
    private Double percentualBaseCalculo;
    private Integer tabelaPreco;
    @NotBlank @Size(max = 80)
    private String descricao;
    @NotBlank @Size(max = 25)
    private String referencia;
    private Double percentualDescontoPadrao;
    private Double quantidadePadrao;
    private String observacoes;
    @Size(max = 20)
    private String ncm;
    private Integer situacaoTributaria;
    private Integer codigoCfo;
    private String cCest;
    private Integer cstPis;
    private Double percentualPis;
    private Integer cstCofins;
    private Double percentualCofins;
}
