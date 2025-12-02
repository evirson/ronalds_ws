package br.com.vetorsistemas.ronalds_ws.cadastro.produto.mapper;

import br.com.vetorsistemas.ronalds_ws.cadastro.produto.Produto;
import br.com.vetorsistemas.ronalds_ws.cadastro.produto.dto.ProdutoDto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {
    public ProdutoDto toDTO(Produto p) {
        if (p == null) return null;
        return ProdutoDto.builder()
                .id(p.getId())
                .tipoProduto(p.getTipoProduto())
                .idFabricante(p.getIdFabricante())
                .idClasse(p.getIdClasse())
                .idGrupo(p.getIdGrupo())
                .idSubGrupo(p.getIdSubGrupo())
                .unidade(p.getUnidade())
                .custo(p.getCusto())
                .precoVista(p.getPrecoVista())
                .prazoGarantiaMeses(p.getPrazoGarantiaMeses())
                .percentualLucro(p.getPercentualLucro())
                .percentualIcm(p.getPercentualIcm())
                .percentualIpi(p.getPercentualIpi())
                .percentualBaseCalculo(p.getPercentualBaseCalculo())
                .tabelaPreco(p.getTabelaPreco())
                .descricao(p.getDescricao())
                .referencia(p.getReferencia())
                .percentualDescontoPadrao(p.getPercentualDescontoPadrao())
                .quantidadePadrao(p.getQuantidadePadrao())
                .observacoes(p.getObservacoes())
                .ncm(p.getNcm())
                .situacaoTributaria(p.getSituacaoTributaria())
                .codigoCfo(p.getCodigoCfo())
                .cCest(p.getCCest())
                .cstPis(p.getCstPis())
                .percentualPis(p.getPercentualPis())
                .cstCofins(p.getCstCofins())
                .percentualCofins(p.getPercentualCofins())
                .build();
    }

    public Produto fromDTO(ProdutoDto d) {
        if (d == null) return null;

        if (d.getQuantidadePadrao() == 0.00) {
            d.setQuantidadePadrao(1.00);
        }

        return Produto.builder()
                .id(d.getId())
                .tipoProduto(d.getTipoProduto())
                .idFabricante(d.getIdFabricante())
                .idClasse(d.getIdClasse())
                .idGrupo(d.getIdGrupo())
                .idSubGrupo(d.getIdSubGrupo())
                .unidade(d.getUnidade())
                .custo(d.getCusto())
                .precoVista(d.getPrecoVista())
                .prazoGarantiaMeses(d.getPrazoGarantiaMeses())
                .percentualLucro(d.getPercentualLucro())
                .percentualIcm(d.getPercentualIcm())
                .percentualIpi(d.getPercentualIpi())
                .percentualBaseCalculo(d.getPercentualBaseCalculo())
                .tabelaPreco(d.getTabelaPreco())
                .descricao(d.getDescricao())
                .referencia(d.getReferencia())
                .percentualDescontoPadrao(d.getPercentualDescontoPadrao())
                .quantidadePadrao(d.getQuantidadePadrao())
                .observacoes(d.getObservacoes())
                .ncm(d.getNcm())
                .situacaoTributaria(d.getSituacaoTributaria())
                .codigoCfo(d.getCodigoCfo())
                .cCest(d.getCCest())
                .cstPis(d.getCstPis())
                .percentualPis(d.getPercentualPis())
                .cstCofins(d.getCstCofins())
                .percentualCofins(d.getPercentualCofins())
                .build();
    }

    public void updateEntityFromDTO(ProdutoDto d, Produto e) {
        if (d == null || e == null) return;
        e.setTipoProduto(d.getTipoProduto());
        e.setIdFabricante(d.getIdFabricante());
        e.setIdClasse(d.getIdClasse());
        e.setIdGrupo(d.getIdGrupo());
        e.setIdSubGrupo(d.getIdSubGrupo());
        e.setUnidade(d.getUnidade());
        e.setCusto(d.getCusto());
        e.setPrecoVista(d.getPrecoVista());
        e.setPrazoGarantiaMeses(d.getPrazoGarantiaMeses());
        e.setPercentualLucro(d.getPercentualLucro());
        e.setPercentualIcm(d.getPercentualIcm());
        e.setPercentualIpi(d.getPercentualIpi());
        e.setPercentualBaseCalculo(d.getPercentualBaseCalculo());
        e.setTabelaPreco(d.getTabelaPreco());
        e.setDescricao(d.getDescricao());
        e.setReferencia(d.getReferencia());
        e.setPercentualDescontoPadrao(d.getPercentualDescontoPadrao());
        e.setQuantidadePadrao(d.getQuantidadePadrao());
        e.setObservacoes(d.getObservacoes());
        e.setNcm(d.getNcm());
        e.setSituacaoTributaria(d.getSituacaoTributaria());
        e.setCodigoCfo(d.getCodigoCfo());
        e.setCCest(d.getCCest());
        e.setCstPis(d.getCstPis());
        e.setPercentualPis(d.getPercentualPis());
        e.setCstCofins(d.getCstCofins());
        e.setPercentualCofins(d.getPercentualCofins());
    }
}

