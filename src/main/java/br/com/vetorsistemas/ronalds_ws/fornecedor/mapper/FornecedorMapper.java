package br.com.vetorsistemas.ronalds_ws.fornecedor.mapper;

import br.com.vetorsistemas.ronalds_ws.fornecedor.Fornecedor;
import br.com.vetorsistemas.ronalds_ws.fornecedor.dto.FornecedorCreateUpdateDTO;
import br.com.vetorsistemas.ronalds_ws.fornecedor.dto.FornecedorDTO;
import org.springframework.stereotype.Component;

@Component
public class FornecedorMapper {

    public FornecedorDTO toDTO(Fornecedor f) {

        if (f == null) return null;
        return FornecedorDTO.builder()
                .id(f.getId())
                .razaoSocial(f.getRazaoSocial())
                .nomeFantasia(f.getNomeFantasia())
                .cnpj(f.getCnpj())
                .inscricaoEstadual(f.getInscricaoEstadual())
                .endereco(f.getEndereco())
                .bairro(f.getBairro())
                .cidade(f.getCidade())
                .estado(f.getEstado())
                .cep(f.getCep())
                .fone(f.getFone())
                .fax(f.getFax())
                .email(f.getEmail())
                .contato(f.getContato())
                .representante(f.getRepresentante())
                .foneRepresentante(f.getFoneRepresentante())
                .faxRepresentante(f.getFaxRepresentante())
                .celularRepresentante(f.getCelularRepresentante())
                .condicaoPagamento(f.getCondicaoPagamento())
                .acrescimo1(f.getAcrescimo1())
                .acrescimo2(f.getAcrescimo2())
                .acrescimo3(f.getAcrescimo3())
                .acrescimo4(f.getAcrescimo4())
                .acrescimo5(f.getAcrescimo5())
                .desconto1(f.getDesconto1())
                .desconto2(f.getDesconto2())
                .desconto3(f.getDesconto3())
                .desconto4(f.getDesconto4())
                .desconto5(f.getDesconto5())
                .percentualLucro(f.getPercentualLucro())
                .tipoFrete(f.getTipoFrete())
                .tipoFornecedor(f.getTipoFornecedor())
                .planoContas(f.getPlanoContas())
                .build();
    }

    public Fornecedor fromCreateUpdateDTO(FornecedorCreateUpdateDTO d) {
        if (d == null) return null;
        return Fornecedor.builder()
                .id(d.getId())
                .razaoSocial(d.getRazaoSocial())
                .nomeFantasia(d.getNomeFantasia())
                .cnpj(d.getCnpj())
                .inscricaoEstadual(d.getInscricaoEstadual())
                .endereco(d.getEndereco())
                .bairro(d.getBairro())
                .cidade(d.getCidade())
                .estado(d.getEstado())
                .cep(d.getCep())
                .fone(d.getFone())
                .fax(d.getFax())
                .email(d.getEmail())
                .contato(d.getContato())
                .representante(d.getRepresentante())
                .foneRepresentante(d.getFoneRepresentante())
                .faxRepresentante(d.getFaxRepresentante())
                .celularRepresentante(d.getCelularRepresentante())
                .condicaoPagamento(d.getCondicaoPagamento())
                .acrescimo1(d.getAcrescimo1())
                .acrescimo2(d.getAcrescimo2())
                .acrescimo3(d.getAcrescimo3())
                .acrescimo4(d.getAcrescimo4())
                .acrescimo5(d.getAcrescimo5())
                .desconto1(d.getDesconto1())
                .desconto2(d.getDesconto2())
                .desconto3(d.getDesconto3())
                .desconto4(d.getDesconto4())
                .desconto5(d.getDesconto5())
                .percentualLucro(d.getPercentualLucro())
                .tipoFrete(d.getTipoFrete())
                .tipoFornecedor(d.getTipoFornecedor())
                .planoContas(d.getPlanoContas())
                .build();
    }

    public void updateEntityFromDTO(FornecedorCreateUpdateDTO dto, Fornecedor entity) {
        if (dto == null || entity == null) return;

        entity.setRazaoSocial(dto.getRazaoSocial());
        entity.setNomeFantasia(dto.getNomeFantasia());
        entity.setCnpj(dto.getCnpj());
        entity.setInscricaoEstadual(dto.getInscricaoEstadual());
        entity.setEndereco(dto.getEndereco());
        entity.setBairro(dto.getBairro());
        entity.setCidade(dto.getCidade());
        entity.setEstado(dto.getEstado());
        entity.setCep(dto.getCep());
        entity.setFone(dto.getFone());
        entity.setFax(dto.getFax());
        entity.setEmail(dto.getEmail());
        entity.setContato(dto.getContato());
        entity.setRepresentante(dto.getRepresentante());
        entity.setFoneRepresentante(dto.getFoneRepresentante());
        entity.setFaxRepresentante(dto.getFaxRepresentante());
        entity.setCelularRepresentante(dto.getCelularRepresentante());
        entity.setCondicaoPagamento(dto.getCondicaoPagamento());
        entity.setAcrescimo1(dto.getAcrescimo1());
        entity.setAcrescimo2(dto.getAcrescimo2());
        entity.setAcrescimo3(dto.getAcrescimo3());
        entity.setAcrescimo4(dto.getAcrescimo4());
        entity.setAcrescimo5(dto.getAcrescimo5());
        entity.setDesconto1(dto.getDesconto1());
        entity.setDesconto2(dto.getDesconto2());
        entity.setDesconto3(dto.getDesconto3());
        entity.setDesconto4(dto.getDesconto4());
        entity.setDesconto5(dto.getDesconto5());
        entity.setPercentualLucro(dto.getPercentualLucro());
        entity.setTipoFrete(dto.getTipoFrete());
        entity.setTipoFornecedor(dto.getTipoFornecedor());
        entity.setPlanoContas(dto.getPlanoContas());
    }
}
