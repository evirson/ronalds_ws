package br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.itemOrdemServico.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemOrdemServicoDTO {

    private Integer id;
    private Integer codigoOrdemServico;
    private Integer codigoProduto;
    private String referenciaProduto;
    private String tipoProduto;
    private String descricaoProduto;
    private Double quantidade;
    private Double precoUnitario;
    private Double percentualDesconto;
    private Double valorDesconto;
    private Double valorLiquido;
    private Double valorBruto;
    private Integer codigoCfo;
}
