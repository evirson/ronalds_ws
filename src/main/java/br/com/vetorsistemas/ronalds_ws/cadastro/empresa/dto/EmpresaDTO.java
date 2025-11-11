package br.com.vetorsistemas.ronalds_ws.cadastro.empresa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpresaDTO {
    private Integer id;
    private Integer naturezaOperacao;
    private Integer regimeTributario;
    private Integer optanteSimples;
    private Integer incCul;
    private Integer inscricaoMunicipal;
    @NotBlank @Size(max = 115)
    private String razaoSocial;
    @NotBlank @Size(max = 60)
    private String fantasia;
    @NotBlank @Size(max = 14) @Pattern(regexp = "\\d*", message = "cpfCnpj deve conter apenas números")
    private String cpfCnpj;
    @NotBlank @Size(max = 125)
    private String endereco;
    @NotBlank
    private String numero;
    private String complemento;
    @NotBlank @Size(max = 60)
    private String bairro;
    @NotBlank @Size(max = 60)
    private String cidade;
    private Integer codigoMunicipio;
    @NotBlank @Size(max = 2)
    private String estado;
    private Integer cep;
    @Email @Size(max = 80)
    private String email;
    @Size(max = 12) @Pattern(regexp = "\\d*", message = "fone deve conter apenas números")
    private String fone;
    private Integer tipoTributacao;
    private Double totalAcumuladoVendido;
}
