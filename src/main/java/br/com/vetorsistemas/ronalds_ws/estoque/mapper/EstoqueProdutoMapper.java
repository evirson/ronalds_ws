package br.com.vetorsistemas.ronalds_ws.estoque.mapper;

import br.com.vetorsistemas.ronalds_ws.estoque.EstoqueProduto;
import br.com.vetorsistemas.ronalds_ws.estoque.dto.EstoqueProdutoDto;
import org.springframework.stereotype.Component;

@Component
public class EstoqueProdutoMapper {
    public EstoqueProdutoDto toDTO(EstoqueProduto e) {
        String nome = null;
        if (e.getProduto() != null) {
            nome = e.getProduto().getDescricao();
        }
        return EstoqueProdutoDto.builder()
                .codigo(e.getId())
                .nomeProduto(nome)
                .quantidadeEstoque(e.getQuantidadeEstoque())
                .estoqueReservado(e.getEstoqueReservado())
                .estoqueConsignado(e.getEstoqueConsignado())
                .dataCadastro(e.getDataCadastro())
                .dataAtualizacao(e.getDataAtualizacao())
                .build();
    }
}

