package br.com.vetorsistemas.ronalds_ws.funcionario;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Table(name = "CADFUN")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gt_cadfun_codfun")
    @SequenceGenerator(
            name = "gt_cadfun_codfun",
            sequenceName = "gt_cadfun_codfun", // nome real da sequência no banco
            allocationSize = 1                     // incrementa de 1 em 1
    )
    @Column(name = "CODFUN")
    private Integer id;
    @Column(name = "NOMFUN", length = 50)
    private String nome;
    @Column(name = "CPFFUN", length = 13)
    private String cpf;
    @Column(name = "CARFUN", length = 25)
            private String cargo;
    @Column(name = "DATADM")
            @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime dataAdmissao;
    @Column(name = "DATNAS")
            @JsonFormat(pattern = "dd-MM-yyyy")
            private LocalDateTime dataNascimento;
    @Column(name = "DATDEM")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime dataDemissao;
    @Column(name = "DATCAD", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime dataCadastro;
    @Column(name = "DATAUT", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime dataAlteracao;
    @Column(name = "COMPEC")
            private Double comissaoPecas;
    @Column(name = "COMMOB")
    private Double comissaoMaoObra;
    @Column(name = "DOCFUN", length = 18)
    private String documento;
    @Column(name = "ENDFUN", length = 50)
    private String endereco;
    @Column(name = "ENDFUN", length = 25)
    private String bairro;
    @Column(name = "CEPFUN", length = 8)
    private String cep;
    @Column(name = "CIDFUN", length = 25)
    private String cidade;
    @Column(name = "ESTFUN", length = 2)
    private String estado;
    @Column(name = "TELFUN", length = 12)
    private String fone;
    @Column(name = "CELFUN", length = 12)
    private String celular;

}
