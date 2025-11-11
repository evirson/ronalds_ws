package br.com.vetorsistemas.ronalds_ws.cadastro.condicaoPagVenda.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CondicaoPagVendaDto {
    private Integer id;
    @NotBlank @Size(max = 50)
    private String descricao;
    private Integer quantidadeParcelas;
    private Integer intervaloParcelas;
    private Double indice;
    private Double taxaJuros;
    private LocalDateTime dataCadastro;
    private LocalDateTime dataAlteracao;
    private String temEntrada;
}
