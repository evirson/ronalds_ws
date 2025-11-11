package br.com.vetorsistemas.ronalds_ws.cadastro.empresa.mapper;

import br.com.vetorsistemas.ronalds_ws.cadastro.empresa.Empresa;
import br.com.vetorsistemas.ronalds_ws.cadastro.empresa.dto.EmpresaDTO;
import org.springframework.stereotype.Component;

@Component
public class EmpresaMapper {
    public EmpresaDTO toDTO(Empresa e) {
        if (e == null) return null;
        return EmpresaDTO.builder()
                .id(e.getId())
                .naturezaOperacao(e.getNaturezaOperacao())
                .regimeTributario(e.getRegimeTributario())
                .optanteSimples(e.getOptanteSimples())
                .incCul(e.getIncCul())
                .inscricaoMunicipal(e.getInscricaoMunicipal())
                .razaoSocial(e.getRazaoSocial())
                .fantasia(e.getFantasia())
                .cpfCnpj(e.getCpfCnpj())
                .endereco(e.getEndereco())
                .numero(e.getNumero())
                .complemento(e.getComplemento())
                .bairro(e.getBairro())
                .cidade(e.getCidade())
                .codigoMunicipio(e.getCodigoMunicipio())
                .estado(e.getEstado())
                .cep(e.getCep())
                .email(e.getEmail())
                .fone(e.getFone())
                .tipoTributacao(e.getTipoTributacao())
                .totalAcumuladoVendido(e.getTotalAcumuladoVendido())
                .build();
    }

    public Empresa fromDTO(EmpresaDTO d) {
        if (d == null) return null;
        return Empresa.builder()
                .id(d.getId())
                .naturezaOperacao(d.getNaturezaOperacao())
                .regimeTributario(d.getRegimeTributario())
                .optanteSimples(d.getOptanteSimples())
                .incCul(d.getIncCul())
                .inscricaoMunicipal(d.getInscricaoMunicipal())
                .razaoSocial(d.getRazaoSocial())
                .fantasia(d.getFantasia())
                .cpfCnpj(d.getCpfCnpj())
                .endereco(d.getEndereco())
                .numero(d.getNumero())
                .complemento(d.getComplemento())
                .bairro(d.getBairro())
                .cidade(d.getCidade())
                .codigoMunicipio(d.getCodigoMunicipio())
                .estado(d.getEstado())
                .cep(d.getCep())
                .email(d.getEmail())
                .fone(d.getFone())
                .tipoTributacao(d.getTipoTributacao())
                .totalAcumuladoVendido(d.getTotalAcumuladoVendido())
                .build();
    }

    public void updateEntityFromDTO(EmpresaDTO d, Empresa e) {
        if (d == null || e == null) return;
        e.setNaturezaOperacao(d.getNaturezaOperacao());
        e.setRegimeTributario(d.getRegimeTributario());
        e.setOptanteSimples(d.getOptanteSimples());
        e.setIncCul(d.getIncCul());
        e.setInscricaoMunicipal(d.getInscricaoMunicipal());
        e.setRazaoSocial(d.getRazaoSocial());
        e.setFantasia(d.getFantasia());
        e.setCpfCnpj(d.getCpfCnpj());
        e.setEndereco(d.getEndereco());
        e.setNumero(d.getNumero());
        e.setComplemento(d.getComplemento());
        e.setBairro(d.getBairro());
        e.setCidade(d.getCidade());
        e.setCodigoMunicipio(d.getCodigoMunicipio());
        e.setEstado(d.getEstado());
        e.setCep(d.getCep());
        e.setEmail(d.getEmail());
        e.setFone(d.getFone());
        e.setTipoTributacao(d.getTipoTributacao());
        e.setTotalAcumuladoVendido(d.getTotalAcumuladoVendido());
    }
}

