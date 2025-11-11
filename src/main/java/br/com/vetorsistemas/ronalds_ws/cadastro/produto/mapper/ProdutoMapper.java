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
                .custoIndice(p.getCustoIndice())
                .precoVista(p.getPrecoVista())
                .descontoVista(p.getDescontoVista())
                .ativaPrecoVista(p.getAtivaPrecoVista())
                .inicioUsoPrecoVista(p.getInicioUsoPrecoVista())
                .finalUsoPrecoVista(p.getFinalUsoPrecoVista())
                .precoPrazo(p.getPrecoPrazo())
                .descontoPrazo(p.getDescontoPrazo())
                .ativaPrecoPrazo(p.getAtivaPrecoPrazo())
                .inicioUsoPrecoPrazo(p.getInicioUsoPrecoPrazo())
                .finalUsoPrecoPrazo(p.getFinalUsoPrecoPrazo())
                .prazoGarantiaMeses(p.getPrazoGarantiaMeses())
                .baixaEstoque(p.getBaixaEstoque())
                .curvaAbc(p.getCurvaAbc())
                .percentualLucro(p.getPercentualLucro())
                .custoMedio(p.getCustoMedio())
                .ultimaCompra(p.getUltimaCompra())
                .ultimaVenda(p.getUltimaVenda())
                .classificacaoOrigem(p.getClassificacaoOrigem())
                .classificacaoTributaria(p.getClassificacaoTributaria())
                .percentualIcm(p.getPercentualIcm())
                .percentualIpi(p.getPercentualIpi())
                .percentualBaseCalculo(p.getPercentualBaseCalculo())
                .classificaoFiscal(p.getClassificaoFiscal())
                .tabelaPreco(p.getTabelaPreco())
                .dataCadastro(p.getDataCadastro())
                .dataAtualizacao(p.getDataAtualizacao())
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
        return Produto.builder()
                .id(d.getId())
                .tipoProduto(d.getTipoProduto())
                .idFabricante(d.getIdFabricante())
                .idClasse(d.getIdClasse())
                .idGrupo(d.getIdGrupo())
                .idSubGrupo(d.getIdSubGrupo())
                .unidade(d.getUnidade())
                .custo(d.getCusto())
                .custoIndice(d.getCustoIndice())
                .precoVista(d.getPrecoVista())
                .descontoVista(d.getDescontoVista())
                .ativaPrecoVista(d.getAtivaPrecoVista())
                .inicioUsoPrecoVista(d.getInicioUsoPrecoVista())
                .finalUsoPrecoVista(d.getFinalUsoPrecoVista())
                .precoPrazo(d.getPrecoPrazo())
                .descontoPrazo(d.getDescontoPrazo())
                .ativaPrecoPrazo(d.getAtivaPrecoPrazo())
                .inicioUsoPrecoPrazo(d.getInicioUsoPrecoPrazo())
                .finalUsoPrecoPrazo(d.getFinalUsoPrecoPrazo())
                .prazoGarantiaMeses(d.getPrazoGarantiaMeses())
                .baixaEstoque(d.getBaixaEstoque())
                .curvaAbc(d.getCurvaAbc())
                .percentualLucro(d.getPercentualLucro())
                .custoMedio(d.getCustoMedio())
                .ultimaCompra(d.getUltimaCompra())
                .ultimaVenda(d.getUltimaVenda())
                .classificacaoOrigem(d.getClassificacaoOrigem())
                .classificacaoTributaria(d.getClassificacaoTributaria())
                .percentualIcm(d.getPercentualIcm())
                .percentualIpi(d.getPercentualIpi())
                .percentualBaseCalculo(d.getPercentualBaseCalculo())
                .classificaoFiscal(d.getClassificaoFiscal())
                .tabelaPreco(d.getTabelaPreco())
                .dataCadastro(d.getDataCadastro())
                .dataAtualizacao(d.getDataAtualizacao())
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
        e.setCustoIndice(d.getCustoIndice());
        e.setPrecoVista(d.getPrecoVista());
        e.setDescontoVista(d.getDescontoVista());
        e.setAtivaPrecoVista(d.getAtivaPrecoVista());
        e.setInicioUsoPrecoVista(d.getInicioUsoPrecoVista());
        e.setFinalUsoPrecoVista(d.getFinalUsoPrecoVista());
        e.setPrecoPrazo(d.getPrecoPrazo());
        e.setDescontoPrazo(d.getDescontoPrazo());
        e.setAtivaPrecoPrazo(d.getAtivaPrecoPrazo());
        e.setInicioUsoPrecoPrazo(d.getInicioUsoPrecoPrazo());
        e.setFinalUsoPrecoPrazo(d.getFinalUsoPrecoPrazo());
        e.setPrazoGarantiaMeses(d.getPrazoGarantiaMeses());
        e.setBaixaEstoque(d.getBaixaEstoque());
        e.setCurvaAbc(d.getCurvaAbc());
        e.setPercentualLucro(d.getPercentualLucro());
        e.setCustoMedio(d.getCustoMedio());
        e.setUltimaCompra(d.getUltimaCompra());
        e.setUltimaVenda(d.getUltimaVenda());
        e.setClassificacaoOrigem(d.getClassificacaoOrigem());
        e.setClassificacaoTributaria(d.getClassificacaoTributaria());
        e.setPercentualIcm(d.getPercentualIcm());
        e.setPercentualIpi(d.getPercentualIpi());
        e.setPercentualBaseCalculo(d.getPercentualBaseCalculo());
        e.setClassificaoFiscal(d.getClassificaoFiscal());
        e.setTabelaPreco(d.getTabelaPreco());
        e.setDataCadastro(d.getDataCadastro());
        e.setDataAtualizacao(d.getDataAtualizacao());
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

