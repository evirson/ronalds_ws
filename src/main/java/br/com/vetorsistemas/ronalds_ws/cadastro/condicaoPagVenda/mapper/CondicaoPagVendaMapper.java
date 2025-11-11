package br.com.vetorsistemas.ronalds_ws.cadastro.condicaoPagVenda.mapper;

import br.com.vetorsistemas.ronalds_ws.cadastro.condicaoPagVenda.CondicaoPagVenda;
import br.com.vetorsistemas.ronalds_ws.cadastro.condicaoPagVenda.dto.CondicaoPagVendaDto;
import org.springframework.stereotype.Component;

@Component
public class CondicaoPagVendaMapper {
    public CondicaoPagVendaDto toDTO(CondicaoPagVenda c) {
        if (c == null) return null;
        return CondicaoPagVendaDto.builder()
                .id(c.getId())
                .descricao(c.getDescricao())
                .quantidadeParcelas(c.getQuantidadeParcelas())
                .intervaloParcelas(c.getIntervaloParcelas())
                .indice(c.getIndice())
                .taxaJuros(c.getTaxaJuros())
                .dataCadastro(c.getDataCadastro())
                .dataAlteracao(c.getDataAlteracao())
                .temEntrada(c.getTemEntrada())
                .build();
    }

    public CondicaoPagVenda fromDTO(CondicaoPagVendaDto d) {
        if (d == null) return null;
        return CondicaoPagVenda.builder()
                .id(d.getId())
                .descricao(d.getDescricao())
                .quantidadeParcelas(d.getQuantidadeParcelas())
                .intervaloParcelas(d.getIntervaloParcelas())
                .indice(d.getIndice())
                .taxaJuros(d.getTaxaJuros())
                .dataCadastro(d.getDataCadastro())
                .dataAlteracao(d.getDataAlteracao())
                .temEntrada(d.getTemEntrada())
                .build();
    }

    public void updateEntityFromDTO(CondicaoPagVendaDto d, CondicaoPagVenda e) {
        if (d == null || e == null) return;
        e.setDescricao(d.getDescricao());
        e.setQuantidadeParcelas(d.getQuantidadeParcelas());
        e.setIntervaloParcelas(d.getIntervaloParcelas());
        e.setIndice(d.getIndice());
        e.setTaxaJuros(d.getTaxaJuros());
        e.setDataCadastro(d.getDataCadastro());
        e.setDataAlteracao(d.getDataAlteracao());
        e.setTemEntrada(d.getTemEntrada());
    }
}

