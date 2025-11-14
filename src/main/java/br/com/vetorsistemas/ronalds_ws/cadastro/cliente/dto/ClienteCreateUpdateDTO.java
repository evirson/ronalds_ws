package br.com.vetorsistemas.ronalds_ws.cadastro.cliente.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteCreateUpdateDTO {
    private Integer id;

    @Size(max = 9)
    private String sexo;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataFundacaoOuNascimento;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate clienteDesde;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataCadastro;

    @Size(max = 50)
    private String nomeFantasia;

    @Size(max = 18)
    private String inscricaoEstadualDocumento;

    @Size(max = 14)
    @Pattern(regexp = "\\d*", message = "cnpjCpf deve conter apenas números")
    private String cnpjCpf;

    @Size(max = 15)
    private String carteiraProfissional;

    @Size(max = 14)
    private String estadoCivil;

    @Size(max = 1)
    private String sedeResidenciaPropria;

    private Integer codigoClasse;

    @Size(max = 18)
    private String inscricaoMunicipal;

    private Double valorAluguel;
    private Double tempoResidencia;

    @Size(max = 8)
    @Pattern(regexp = "\\d*", message = "cep deve conter apenas números")
    private String cep;

    @Size(max = 50)
    private String endereco;

    @Size(max = 50)
    private String pontoReferencia;

    @Size(max = 25)
    private String bairro;

    @Size(max = 25)
    private String cidade;

    @Size(max = 2)
    private String estado;

    @Size(max = 20)
    @Pattern(regexp = "\\d*", message = "telefone deve conter apenas números")
    private String telefone;

    @Size(max = 20)
    @Pattern(regexp = "\\d*", message = "fax deve conter apenas números")
    private String fax;

    @Size(max = 20)
    @Pattern(regexp = "\\d*", message = "celular deve conter apenas números")
    private String celular;

    @Size(max = 1000)
    private String observacoes;

    @NotBlank
    @Size(max = 1)
    private String tipoCliente;

    @Size(max = 80)
    private String nomeRazaoSocial;

    @Size(max = 1)
    private String situacao;

    @Email
    @Size(max = 80)
    private String email;

    private Double pis;
    private Double cofins;
    private Double csll;
    private Double irpj;
    private Double iss;
    private Double cofinsServico;
    private Double csllServico;
    private Double irpjServico;
    private Double pisServico;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataNascimento;
}
