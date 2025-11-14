package br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.itemOrdemServico.mapper;

import br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.itemOrdemServico.ItemOrdemServico;
import br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.itemOrdemServico.dto.ItemOrdemServicoCreateUpdateDTO;
import br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.itemOrdemServico.dto.ItemOrdemServicoDTO;
import org.springframework.stereotype.Component;

@Component
public class ItemOrdemServicoMapper {

    public ItemOrdemServicoDTO toDTO(ItemOrdemServico item) {
        if (item == null) return null;

        ItemOrdemServicoDTO dto = ItemOrdemServicoDTO.builder()
                .id(item.getId())
                .codigoOrdemServico(item.getCodigoOrdemServico())
                .codigoProduto(item.getCodigoProduto())
                .tipoProduto(item.getTipoProduto())
                .descricaoProduto(item.getDescricaoProduto())
                .quantidade(item.getQuantidade())
                .precoUnitario(item.getPrecoUnitario())
                .percentualDesconto(item.getPercentualDesconto())
                .valorDesconto(item.getValorDesconto())
                .valorLiquido(item.getValorLiquido())
                .valorBruto(item.getValorBruto())
                .codigoCfo(item.getCodigoCfo())
                .build();

        // Adicionar referência do produto se disponível
        if (item.getProduto() != null) {
            dto.setReferenciaProduto(item.getProduto().getReferencia());
        }

        return dto;
    }

    public ItemOrdemServico fromCreateUpdateDTO(ItemOrdemServicoCreateUpdateDTO dto) {
        if (dto == null) return null;

        return ItemOrdemServico.builder()
                .codigoOrdemServico(dto.getCodigoOrdemServico())
                .codigoProduto(dto.getCodigoProduto())
                .tipoProduto(dto.getTipoProduto())
                .descricaoProduto(dto.getDescricaoProduto())
                .quantidade(dto.getQuantidade())
                .precoUnitario(dto.getPrecoUnitario())
                .percentualDesconto(dto.getPercentualDesconto())
                .valorDesconto(dto.getValorDesconto())
                .valorLiquido(dto.getValorLiquido())
                .valorBruto(dto.getValorBruto())
                .codigoCfo(dto.getCodigoCfo())
                .build();
    }

    public void updateEntityFromDTO(ItemOrdemServicoCreateUpdateDTO dto, ItemOrdemServico entity) {
        if (dto == null || entity == null) return;

        entity.setCodigoOrdemServico(dto.getCodigoOrdemServico());
        entity.setCodigoProduto(dto.getCodigoProduto());
        entity.setTipoProduto(dto.getTipoProduto());
        entity.setDescricaoProduto(dto.getDescricaoProduto());
        entity.setQuantidade(dto.getQuantidade());
        entity.setPrecoUnitario(dto.getPrecoUnitario());
        entity.setPercentualDesconto(dto.getPercentualDesconto());
        entity.setValorDesconto(dto.getValorDesconto());
        entity.setValorLiquido(dto.getValorLiquido());
        entity.setValorBruto(dto.getValorBruto());
        entity.setCodigoCfo(dto.getCodigoCfo());
    }
}
