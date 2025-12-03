package br.com.vetorsistemas.ronalds_ws.movimento.contasPagar.mapper;

import br.com.vetorsistemas.ronalds_ws.movimento.contasPagar.ContasPagar;
import br.com.vetorsistemas.ronalds_ws.movimento.contasPagar.dto.ContasPagarDto;
import org.springframework.stereotype.Component;

@Component
public class ContasPagarMapper {

    public ContasPagar toEntity(ContasPagarDto dto) {

        if (dto == null) {
            return null;
        }

        ContasPagar ent = new ContasPagar();

        ent.setCodigoPedido(dto.getCodigoPedido());
        ent.setDataMovimento(dto.getDataMovimento());
        ent.setPlanoContas(dto.getPlanoContas());
        ent.setIdFornecedor(dto.getIdFornecedor());
        ent.setDocumento(dto.getDocumento());
        ent.setComDuplicata(dto.getComDuplicata());
        ent.setDataVencimento(dto.getDataVencimento());
        ent.setPercentualMulta(dto.getPercentualMulta());
        ent.setPercentualJuros(dto.getPercentualJuros());
        ent.setValorVencimento(dto.getValorVencimento());
        ent.setDescontoVencimento(dto.getDescontoVencimento());
        ent.setValidadeDesconto(dto.getValidadeDesconto());
        ent.setValorPago(dto.getValorPago());
        ent.setObservacoes(dto.getObservacoes());
        ent.setDataPagamento(dto.getDataPagamento());
        ent.setDescricao(dto.getDescricao());

        return ent;

    }

    public ContasPagarDto toDto(ContasPagar ent) {

        if (ent == null) {
            return null;
        }

        ContasPagarDto dto = new ContasPagarDto();

        dto.setId(ent.getId());
        dto.setCodigoPedido(ent.getCodigoPedido());
        dto.setDataMovimento(ent.getDataMovimento());
        dto.setPlanoContas(ent.getPlanoContas());
        dto.setIdFornecedor(ent.getIdFornecedor());
        dto.setDocumento(ent.getDocumento());
        dto.setComDuplicata(ent.getComDuplicata());
        dto.setDataVencimento(ent.getDataVencimento());
        dto.setPercentualMulta(ent.getPercentualMulta());
        dto.setPercentualJuros(ent.getPercentualJuros());
        dto.setValorVencimento(ent.getValorVencimento());
        dto.setDescontoVencimento(ent.getDescontoVencimento());
        dto.setValidadeDesconto(ent.getValidadeDesconto());
        dto.setValorPago(ent.getValorPago());
        dto.setObservacoes(ent.getObservacoes());
        dto.setDataPagamento(ent.getDataPagamento());
        dto.setDescricao(ent.getDescricao());

        return dto;

    }

    public void updateEntityFromDTO(ContasPagarDto dto, ContasPagar ent) {
        if (dto == null || ent == null) return;

        ent.setCodigoPedido(dto.getCodigoPedido());
        ent.setDataMovimento(dto.getDataMovimento());
        ent.setPlanoContas(dto.getPlanoContas());
        ent.setIdFornecedor(dto.getIdFornecedor());
        ent.setDocumento(dto.getDocumento());
        ent.setComDuplicata(dto.getComDuplicata());
        ent.setDataVencimento(dto.getDataVencimento());
        ent.setPercentualMulta(dto.getPercentualMulta());
        ent.setPercentualJuros(dto.getPercentualJuros());
        ent.setValorVencimento(dto.getValorVencimento());
        ent.setDescontoVencimento(dto.getDescontoVencimento());
        ent.setValidadeDesconto(dto.getValidadeDesconto());
        ent.setValorPago(dto.getValorPago());
        ent.setObservacoes(dto.getObservacoes());
        ent.setDataPagamento(dto.getDataPagamento());
        ent.setDescricao(dto.getDescricao());
    }

}
