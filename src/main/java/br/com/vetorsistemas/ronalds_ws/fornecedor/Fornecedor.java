package br.com.vetorsistemas.ronalds_ws.fornecedor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "CADFOR")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fornecedor {

    @Id
    @Column(name="CODFOR")
    private Integer id;
    @Column(name="NOMFOR")
    private String razaoSocial;
    @Column(name="NOMFAN")
    private String nomeFantasia;
    @Column(name="CNPFOR")
    private String cnpj;
    @Column(name="INSFOR")
    private String inscricaoEstadual;
    @Column(name="ENDFOR")
    private String endereco;
    @Column(name="BAIFOR")
    private String bairro;
    @Column(name="CIDFOR")
    private String cidade;
    @Column(name="ESTFOR")
    private String estado;
    @Column(name="CEPFOR")
    private String cep;
    @Column(name="FONFOR")
    private String fone;
    @Column(name="FAXFOR")
    private String fax;
    @Column(name="MAIFOR")
    private String email;
    @Column(name="CONFOR")
    private String contato;
    @Column(name="REPFOR")
    private String representante;
    @Column(name="FONREP")
    private String foneRepresentante;
    @Column(name="FAXREP")
    private String faxRepresentante;
    @Column(name="CELREP")
    private String celularRepresentante;
    @Column(name="CONPAG")
    private String condicaoPagamento;
    @Column(name="ACRF01")
    private Double acrescimo1;
    @Column(name="ACRF02")
    private Double acrescimo2;
    @Column(name="ACRF03")
    private Double acrescimo3;
    @Column(name="ACRF04")
    private Double acrescimo4;
    @Column(name="ACRF05")
    private Double acrescimo5;
    @Column(name="DESC01")
    private Double desconto1;
    @Column(name="DESC02")
    private Double desconto2;
    @Column(name="DESC03")
    private Double desconto3;
    @Column(name="DESC04")
    private Double desconto4;
    @Column(name="DESC05")
    private Double desconto5;
    @Column(name="PERLUC")
    private Double percentualLucro;
    @Column(name="TIPFRE")
    private String tipoFrete;
    @Column(name="TIPFOR")
    private String tipoFornecedor;
    @Column(name="CODPLA")
    private String planoContas;

}
