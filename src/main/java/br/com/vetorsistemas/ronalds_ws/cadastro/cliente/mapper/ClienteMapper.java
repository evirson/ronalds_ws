package br.com.vetorsistemas.ronalds_ws.cadastro.cliente.mapper;

import br.com.vetorsistemas.ronalds_ws.cadastro.cliente.Cliente;
import br.com.vetorsistemas.ronalds_ws.cadastro.cliente.dto.ClienteCreateUpdateDTO;
import br.com.vetorsistemas.ronalds_ws.cadastro.cliente.dto.ClienteDTO;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

    public ClienteDTO toDTO(Cliente c) {
        if (c == null) return null;
        return ClienteDTO.builder()
                .id(c.getId())
                .sexo(c.getSexo())
                .dataFundacaoOuNascimento(c.getDataFundacaoOuNascimento())
                .clienteDesde(c.getClienteDesde())
                .dataCadastro(c.getDataCadastro())
                .nomeFantasia(c.getNomeFantasia())
                .inscricaoEstadualDocumento(c.getInscricaoEstadualDocumento())
                .cnpjCpf(c.getCnpjCpf())
                .carteiraProfissional(c.getCarteiraProfissional())
                .estadoCivil(c.getEstadoCivil())
                .sedeResidenciaPropria(c.getSedeResidenciaPropria())
                .codigoClasse(c.getCodigoClasse())
                .inscricaoMunicipal(c.getInscricaoMunicipal())
                .valorAluguel(c.getValorAluguel())
                .tempoResidencia(c.getTempoResidencia())
                .cep(c.getCep())
                .endereco(c.getEndereco())
                .pontoReferencia(c.getPontoReferencia())
                .bairro(c.getBairro())
                .cidade(c.getCidade())
                .estado(c.getEstado())
                .telefone(c.getTelefone())
                .fax(c.getFax())
                .celular(c.getCelular())
                .observacoes(c.getObservacoes())
                .tipoCliente(c.getTipoCliente())
                .nomeRazaoSocial(c.getNomeRazaoSocial())
                .situacao(c.getSituacao())
                .email(c.getEmail())
                .pis(c.getPis())
                .cofins(c.getCofins())
                .csll(c.getCsll())
                .irpj(c.getIrpj())
                .iss(c.getIss())
                .cofinsServico(c.getCofinsServico())
                .csllServico(c.getCsllServico())
                .irpjServico(c.getIrpjServico())
                .pisServico(c.getPisServico())
                .dataNascimento(c.getDataNascimento())
                .build();
    }

    public Cliente fromCreateUpdateDTO(ClienteCreateUpdateDTO d) {
        if (d == null) return null;
        return Cliente.builder()
                .id(d.getId())
                .sexo(d.getSexo())
                .dataFundacaoOuNascimento(d.getDataFundacaoOuNascimento())
                .clienteDesde(d.getClienteDesde())
                .dataCadastro(d.getDataCadastro())
                .nomeFantasia(d.getNomeFantasia())
                .inscricaoEstadualDocumento(d.getInscricaoEstadualDocumento())
                .cnpjCpf(d.getCnpjCpf())
                .carteiraProfissional(d.getCarteiraProfissional())
                .estadoCivil(d.getEstadoCivil())
                .sedeResidenciaPropria(d.getSedeResidenciaPropria())
                .codigoClasse(d.getCodigoClasse())
                .inscricaoMunicipal(d.getInscricaoMunicipal())
                .valorAluguel(d.getValorAluguel())
                .tempoResidencia(d.getTempoResidencia())
                .cep(d.getCep())
                .endereco(d.getEndereco())
                .pontoReferencia(d.getPontoReferencia())
                .bairro(d.getBairro())
                .cidade(d.getCidade())
                .estado(d.getEstado())
                .telefone(d.getTelefone())
                .fax(d.getFax())
                .celular(d.getCelular())
                .observacoes(d.getObservacoes())
                .tipoCliente(d.getTipoCliente())
                .nomeRazaoSocial(d.getNomeRazaoSocial())
                .situacao(d.getSituacao())
                .email(d.getEmail())
                .pis(d.getPis())
                .cofins(d.getCofins())
                .csll(d.getCsll())
                .irpj(d.getIrpj())
                .iss(d.getIss())
                .cofinsServico(d.getCofinsServico())
                .csllServico(d.getCsllServico())
                .irpjServico(d.getIrpjServico())
                .pisServico(d.getPisServico())
                .dataNascimento(d.getDataNascimento())
                .build();
    }

    public void updateEntityFromDTO(ClienteCreateUpdateDTO d, Cliente e) {
        if (d == null || e == null) return;
        e.setSexo(d.getSexo());
        e.setDataFundacaoOuNascimento(d.getDataFundacaoOuNascimento());
        e.setClienteDesde(d.getClienteDesde());
        e.setDataCadastro(d.getDataCadastro());
        e.setNomeFantasia(d.getNomeFantasia());
        e.setInscricaoEstadualDocumento(d.getInscricaoEstadualDocumento());
        e.setCnpjCpf(d.getCnpjCpf());
        e.setCarteiraProfissional(d.getCarteiraProfissional());
        e.setEstadoCivil(d.getEstadoCivil());
        e.setSedeResidenciaPropria(d.getSedeResidenciaPropria());
        e.setCodigoClasse(d.getCodigoClasse());
        e.setInscricaoMunicipal(d.getInscricaoMunicipal());
        e.setValorAluguel(d.getValorAluguel());
        e.setTempoResidencia(d.getTempoResidencia());
        e.setCep(d.getCep());
        e.setEndereco(d.getEndereco());
        e.setPontoReferencia(d.getPontoReferencia());
        e.setBairro(d.getBairro());
        e.setCidade(d.getCidade());
        e.setEstado(d.getEstado());
        e.setTelefone(d.getTelefone());
        e.setFax(d.getFax());
        e.setCelular(d.getCelular());
        e.setObservacoes(d.getObservacoes());
        e.setTipoCliente(d.getTipoCliente());
        e.setNomeRazaoSocial(d.getNomeRazaoSocial());
        e.setSituacao(d.getSituacao());
        e.setEmail(d.getEmail());
        e.setPis(d.getPis());
        e.setCofins(d.getCofins());
        e.setCsll(d.getCsll());
        e.setIrpj(d.getIrpj());
        e.setIss(d.getIss());
        e.setCofinsServico(d.getCofinsServico());
        e.setCsllServico(d.getCsllServico());
        e.setIrpjServico(d.getIrpjServico());
        e.setPisServico(d.getPisServico());
        e.setDataNascimento(d.getDataNascimento());
    }
}
