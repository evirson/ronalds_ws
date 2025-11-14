package br.com.vetorsistemas.ronalds_ws.cadastro.cliente.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDTO {
    private Integer id;
    private String sexo;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime dataFundacaoOuNascimento;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime clienteDesde;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime dataCadastro;
    private String nomeFantasia;
    private String inscricaoEstadualDocumento;
    private String cnpjCpf;
    private String carteiraProfissional;
    private String estadoCivil;
    private String sedeResidenciaPropria;
    private Integer codigoClasse;
    private String inscricaoMunicipal;
    private Double valorAluguel;
    private Double tempoResidencia;
    private String cep;
    private String endereco;
    private String pontoReferencia;
    private String bairro;
    private String cidade;
    private String estado;
    private String telefone;
    private String fax;
    private String celular;
    private String observacoes;
    private String tipoCliente;
    private String nomeRazaoSocial;
    private String situacao;
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
    private LocalDateTime dataNascimento;
}
