package br.com.vetorsistemas.ronalds_ws.cadastro.empresa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CADEMP")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gt_cademp_codemp")
    @SequenceGenerator(
            name = "gt_cademp_codemp",
            sequenceName = "gt_cademp_codemp", // nome real da sequência no banco
            allocationSize = 1                     // incrementa de 1 em 1
    )
    @Column(name = "CODEMP", nullable = false)
    private Integer id;
    @Column(name = "NATOPR", nullable = false)
    private Integer naturezaOperacao;
    @Column(name = "REGTRI", nullable = false)
    private Integer regimeTributario;
    @Column(name = "OPTSIM", nullable = false)
    private Integer optanteSimples;
    @Column(name = "INCCUL", nullable = false)
    private Integer incCul;
    @Column(name = "INSMUN", length = 15, nullable = false)
    private Integer inscricaoMunicipal;
    @Column(name = "RAZSOC", length = 115, nullable = false)
    private String razaoSocial;
    @Column(name = "NOMFANC", length = 60, nullable = false)
    private String fantasia;
    @Column(name = "CPFCNP", nullable = false, length = 14)
    private String cpfCnpj;
    @Column(name = "ENDEMP", nullable = false, length = 125)
    private String endereco;
    @Column(name = "NUMEMP", nullable = false)
    private String numero;
    @Column(name = "COMEMP", length = 60)
    private String complemento;
    @Column(name = "BAIEMP", nullable = false, length = 60)
    private String bairro;
    @Column(name = "MUNEMP", nullable = false, length = 60)
    private String cidade;
    @Column(name = "CODMUN", nullable = false)
    private Integer codigoMunicipio;
    @Column(name = "ESTEMP", length = 2, nullable = false)
    private String estado;
    @Column(name = "CEPEMP", nullable = false)
    private Integer cep;
    @Column(name = "MAIEMP", nullable = false, length = 80)
    private String email;
    @Column(name = "FONEMP", nullable = false, length = 12)
    private String fone;
    @Column(name = "TIPTRI")
            private Integer tipoTributacao;
    @Column(name = "TOTVEN")
    private Double totalAcumuladoVendido;
}
