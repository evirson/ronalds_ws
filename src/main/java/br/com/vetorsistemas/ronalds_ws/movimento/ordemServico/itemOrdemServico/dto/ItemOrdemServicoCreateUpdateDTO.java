package br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.itemOrdemServico.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemOrdemServicoCreateUpdateDTO {

    // Se id for null = inserção, se id tiver valor = alteração
    private Integer id;

    private Integer codigoOrdemServico;

    @NotNull
    private Integer codigoProduto;

    @Size(max = 1)
    private String tipoProduto;

    @Size(max = 50)
    private String descricaoProduto;

    private Double quantidade;
    private Double precoUnitario;
    private Double percentualDesconto;
    private Double valorDesconto;
    private Double valorLiquido;
    private Double valorBruto;
    private Integer codigoCfo;
}
