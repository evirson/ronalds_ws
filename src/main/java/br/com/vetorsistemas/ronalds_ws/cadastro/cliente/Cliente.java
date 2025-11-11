package br.com.vetorsistemas.ronalds_ws.cadastro.cliente;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "CLIBAS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gt_clibas_codcli")
    @SequenceGenerator(name = "gt_clibas_codcli", sequenceName = "gt_clibas_codcli", allocationSize = 1)
    @Column(name = "CODCLI", nullable = false)
    private Integer id;

    @Column(name = "SEXCLI", length = 9)
    private String sexo;

    @Column(name = "FUNNAS")
    private LocalDateTime dataFundacaoOuNascimento;

    @Column(name = "CLIDES", nullable = false)
    private LocalDateTime clienteDesde;

    @Column(name = "DATCAD", nullable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "FANNAT", length = 50)
    private String nomeFantasia;

    @Column(name = "IESDOC", length = 18)
    private String inscricaoEstadualDocumento;

    @Column(name = "CPNCPF", length = 14)
    private String cnpjCpf;

    @Column(name = "CARPRF", length = 15)
    private String carteiraProfissional;

    @Column(name = "ESTCIV", length = 14)
    private String estadoCivil;

    @Column(name = "SEDRES", length = 1)
    private String sedeResidenciaPropria;

    @Column(name = "CODCLA")
    private Integer codigoClasse;

    @Column(name = "INSMUN", length = 18)
    private String inscricaoMunicipal;

    @Column(name = "VALALU")
    private Double valorAluguel;

    @Column(name = "TMPRES")
    private Double tempoResidencia;

    @Column(name = "CEPCLI", length = 8)
    private String cep;

    @Column(name = "ENDCLI", length = 50)
    private String endereco;

    @Column(name = "REFLOC", length = 50)
    private String pontoReferencia;

    @Column(name = "BAICLI", length = 25)
    private String bairro;

    @Column(name = "CIDCLI", length = 25)
    private String cidade;

    @Column(name = "ESTCLI", length = 2)
    private String estado;

    @Column(name = "TELCLI", length = 20)
    private String telefone;

    @Column(name = "FAXCLI", length = 20)
    private String fax;

    @Column(name = "CELCLI", length = 20)
    private String celular;

    @Column(name = "OBSCLI", length = 1000)
    private String observacoes;

    @Column(name = "TIPCLI", length = 1, nullable = false)
    private String tipoCliente;

    @Column(name = "RAZNOM", length = 80)
    private String nomeRazaoSocial;

    @Column(name = "SITCLI", length = 1)
    private String situacao;

    @Column(name = "MAICLI", length = 80)
    private String email;

    @Column(name = "PIS", nullable = false)
    private Double pis;

    @Column(name = "COFINS", nullable = false)
    private Double cofins;

    @Column(name = "CSLL", nullable = false)
    private Double csll;

    @Column(name = "IRPJ", nullable = false)
    private Double irpj;

    @Column(name = "ISS", nullable = false)
    private Double iss;

    @Column(name = "COFINSSRV", nullable = false)
    private Double cofinsServico;

    @Column(name = "CSLLSRV", nullable = false)
    private Double csllServico;

    @Column(name = "IRPJSRV", nullable = false)
    private Double irpjServico;

    @Column(name = "PISSRV", nullable = false)
    private Double pisServico;

    @Column(name = "DATANASCIMENTO")
    private LocalDateTime dataNascimento;
}
