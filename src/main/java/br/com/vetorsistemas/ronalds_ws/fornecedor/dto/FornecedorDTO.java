package br.com.vetorsistemas.ronalds_ws.fornecedor.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FornecedorDTO {
    private Integer id;
    private String razaoSocial;
    private String nomeFantasia;
    private String cnpj;
    private String inscricaoEstadual;
    private String endereco;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String fone;
    private String fax;
    private String email;
    private String contato;
    private String representante;
    private String foneRepresentante;
    private String faxRepresentante;
    private String celularRepresentante;
    private String condicaoPagamento;
    private Double acrescimo1;
    private Double acrescimo2;
    private Double acrescimo3;
    private Double acrescimo4;
    private Double acrescimo5;
    private Double desconto1;
    private Double desconto2;
    private Double desconto3;
    private Double desconto4;
    private Double desconto5;
    private Double percentualLucro;
    private String tipoFrete;
    private String tipoFornecedor;
    private String planoContas;
}
