package br.com.vetorsistemas.ronalds_ws.fornecedor.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FornecedorCreateUpdateDTO {
    // ID gerado por trigger/gerador (não exigir no DTO)
    private Integer id;

    @NotBlank
    @Size(max = 50)
    private String razaoSocial;

    @NotBlank
    @Size(max = 50)
    private String nomeFantasia;

    // Tabela CADFOR define 14 caracteres (CNPFOR)
    @Size(max = 14)
    @Pattern(regexp = "\\d*", message = "cnpj deve conter apenas números")
    private String cnpj;

    @Size(max = 18)
    private String inscricaoEstadual;

    @Size(max = 50)
    private String endereco;

    @Size(max = 25)
    private String bairro;

    @Size(max = 25)
    private String cidade;

    @Size(max = 2)
    private String estado;

    @Size(max = 8)
    @Pattern(regexp = "\\d*", message = "cep deve conter apenas números")
    private String cep;

    @Size(max = 12)
    @Pattern(regexp = "\\d*", message = "fone deve conter apenas números")
    private String fone;

    @Size(max = 12)
    @Pattern(regexp = "\\d*", message = "fax deve conter apenas números")
    private String fax;

    @Email
    @Size(max = 50)
    private String email;

    @Size(max = 50)
    private String contato;

    @Size(max = 50)
    private String representante;

    @Size(max = 12)
    @Pattern(regexp = "\\d*", message = "Fone representante deve conter apenas números")
    private String foneRepresentante;

    @Size(max = 12)
    @Pattern(regexp = "\\d*", message = "Fax representante deve conter apenas números")
    private String faxRepresentante;

    @Size(max = 12)
    @Pattern(regexp = "\\d*", message = "Celular representante deve conter apenas números")
    private String celularRepresentante;

    @Size(max = 15)
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

    // Comentário no Entity indica CIF/FOB; restringir valores conhecidos
    @Pattern(regexp = "C|F", message = "tipoFrete deve ser C-IF ou F-OB")
    private String tipoFrete;

    private String tipoFornecedor;
    @Pattern(regexp = "\\d*", message = "planoContas deve conter apenas números")
    private String planoContas;
}
