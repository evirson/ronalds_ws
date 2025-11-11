package br.com.vetorsistemas.ronalds_ws.cadastro.funcionario.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private LocalDateTime dataAdmissao;
    private LocalDateTime dataNascimento;
    private LocalDateTime dataDemissao;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAlteracao;
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

