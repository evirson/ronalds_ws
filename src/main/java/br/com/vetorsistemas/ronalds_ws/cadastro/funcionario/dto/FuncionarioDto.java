package br.com.vetorsistemas.ronalds_ws.cadastro.funcionario.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class FuncionarioDto {
    private Integer id;
    private String nome;
    private String cpf;
    private String cargo;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataAdmissao;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataNascimento;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataDemissao;
    private Double comissaoPecas;
    private Double comissaoMaoObra;
    private String documento;
    private String endereco;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;
    private String fone;
    private String celular;
}

