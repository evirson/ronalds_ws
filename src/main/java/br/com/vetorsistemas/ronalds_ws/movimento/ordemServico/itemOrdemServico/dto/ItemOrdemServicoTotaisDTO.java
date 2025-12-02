package br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.itemOrdemServico.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemOrdemServicoTotaisDTO {

    private Integer codigoOrdemServico;
    private Long quantidadeItens;       // Contagem de linhas
    private Double totalQuantidade;     // Soma do campo quantidade
    private Double totalBruto;          // Soma do valor bruto
    private Double totalDesconto;       // Soma do valor desconto
    private Double totalLiquido;        // Soma do valor líquido
}
