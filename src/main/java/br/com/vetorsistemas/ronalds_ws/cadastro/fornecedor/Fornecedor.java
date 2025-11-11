package br.com.vetorsistemas.ronalds_ws.cadastro.fornecedor;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gt_cadfor_codfor")
    @SequenceGenerator(
            name = "gt_cadfor_codfor",
            sequenceName = "gt_cadfor_codfor", // nome real da sequência no banco
            allocationSize = 1                     // incrementa de 1 em 1
    )
    @Column(name="CODFOR", nullable = false)
    private Integer id;
    @Column(name="NOMFOR", length = 50, nullable = false)
    private String razaoSocial;
    @Column(name="NOMFAN", length = 50, nullable = false)
    private String nomeFantasia;
    @Column(name="CNPFOR", length = 14)
    private String cnpj;
    @Column(name="INSFOR", length = 18)
    private String inscricaoEstadual;
    @Column(name="ENDFOR", length = 50)
    private String endereco;
    @Column(name="BAIFOR", length = 25)
    private String bairro;
    @Column(name="CIDFOR", length = 25)
    private String cidade;
    @Column(name="ESTFOR", length = 2)
    private String estado;
    @Column(name="CEPFOR", length = 8)
    private String cep;
    @Column(name="FONFOR", length = 12)
    private String fone;
    @Column(name="FAXFOR", length = 12)
    private String fax;
    @Column(name="MAIFOR", length = 50)
    private String email;
    @Column(name="CONFOR", length = 50)
    private String contato;
    @Column(name="REPFOR", length = 50)
    private String representante;
    @Column(name="FONREP", length = 12)
    private String foneRepresentante;
    @Column(name="FAXREP", length = 12)
    private String faxRepresentante;
    @Column(name="CELREP", length = 12)
    private String celularRepresentante;
    @Column(name="CONPAG", length = 15)
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
    @Column(name="TIPFRE", length = 1) //CIF/FOB
    private String tipoFrete;
    @Column(name="TIPFOR", length = 1)  //0 - Produto 1 - Serviço 2- Produto / Serviço
    private String tipoFornecedor;
    @Column(name="CODPLA", length = 8)
    private String planoContas;

}
