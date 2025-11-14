package br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.contasReceber.mapper;

import br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.contasReceber.ContasReceber;
import br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.contasReceber.dto.ContasReceberDto;
import org.springframework.stereotype.Component;

@Component
public class ContasReceberMapper {
    public ContasReceberDto toDTO(ContasReceber c) {
        if (c == null) return null;
        return ContasReceberDto.builder()
                .id(c.getId())
                .codigoServico(c.getCodigoServico())
                .idCondicaoPagVenda(c.getIdCondicaoPagVenda())
                .dataMovimento(c.getDataMovimento())
                .descricao(c.getDescricao())
                .idCliente(c.getIdCliente())
                .documento(c.getDocumento())
                .dataVencimenot(c.getDataVencimenot())
                .previs(c.getPrevis())
                .valorReceber(c.getValorReceber())
                .valorIndice(c.getValorIndice())
                .observacoes(c.getObservacoes())
                .valorJuros(c.getValorJuros())
                .valorMulta(c.getValorMulta())
                .valorDesconto(c.getValorDesconto())
                .dataRecebimento(c.getDataRecebimento())
                .valorRecebido(c.getValorRecebido())
                .planoContas(c.getPlanoContas())
                .build();
    }

    public ContasReceber fromDTO(ContasReceberDto d) {
        if (d == null) return null;
        return ContasReceber.builder()
                .id(d.getId())
                .codigoServico(d.getCodigoServico())
                .idCondicaoPagVenda(d.getIdCondicaoPagVenda())
                .dataMovimento(d.getDataMovimento())
                .descricao(d.getDescricao())
                .idCliente(d.getIdCliente())
                .documento(d.getDocumento())
                .dataVencimenot(d.getDataVencimenot())
                .previs(d.getPrevis())
                .ValorReceber(d.getValorReceber())
                .ValorIndice(d.getValorIndice())
                .observacoes(d.getObservacoes())
                .valorJuros(d.getValorJuros())
                .valorMulta(d.getValorMulta())
                .valorDesconto(d.getValorDesconto())
                .dataRecebimento(d.getDataRecebimento())
                .valorRecebido(d.getValorRecebido())
                .planoContas(d.getPlanoContas())
                .build();
    }

    public void updateEntityFromDTO(ContasReceberDto d, ContasReceber e) {
        if (d == null || e == null) return;
        e.setCodigoServico(d.getCodigoServico());
        e.setIdCondicaoPagVenda(d.getIdCondicaoPagVenda());
        e.setDataMovimento(d.getDataMovimento());
        e.setDescricao(d.getDescricao());
        e.setIdCliente(d.getIdCliente());
        e.setDocumento(d.getDocumento());
        e.setDataVencimenot(d.getDataVencimenot());
        e.setPrevis(d.getPrevis());
        e.setValorReceber(d.getValorReceber());
        e.setValorIndice(d.getValorIndice());
        e.setObservacoes(d.getObservacoes());
        e.setValorJuros(d.getValorJuros());
        e.setValorMulta(d.getValorMulta());
        e.setValorDesconto(d.getValorDesconto());
        e.setDataRecebimento(d.getDataRecebimento());
        e.setValorRecebido(d.getValorRecebido());
        e.setPlanoContas(d.getPlanoContas());
    }
}

