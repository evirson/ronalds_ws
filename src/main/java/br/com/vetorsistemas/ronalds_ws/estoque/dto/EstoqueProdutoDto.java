package br.com.vetorsistemas.ronalds_ws.estoque.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EstoqueProdutoDto {
    private Integer codigo;
    private String nomeProduto;
    private Double quantidadeEstoque;
    private Double estoqueReservado;
    private Double estoqueConsignado;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataCadastro;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataAtualizacao;
}

