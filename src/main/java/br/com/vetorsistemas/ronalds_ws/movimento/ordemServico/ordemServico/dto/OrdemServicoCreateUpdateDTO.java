package br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.ordemServico.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdemServicoCreateUpdateDTO {

    @Size(max = 1)
    private String tipoServico;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataAbertura;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime horaAbertura;

    @Size(max = 25)
    private String responsavel;

    private Integer codigoFuncionario;
    private Integer codigoCliente;
    private Integer codigoVeiculo;
    private Integer codigoCondicaoPagamento;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime horaFinalizacao;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataFinalizacao;

    private Integer garantiaPecasMeses;
    private Integer garantiaMaoObraMeses;

    @Size(max = 1)
    private String servicoLiberado;

    private Double totalBruto;
    private Double valorDesconto;
    private Double totalLiquido;

    @Size(max = 1)
    private String faturamento;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataFaturamento;

    @Size(max = 10)
    private String numeroNotaFiscalServico;

    @Size(max = 10)
    private String numeroNotaFiscalProduto;

    @Size(max = 500)
    private String observacoes;

    private Integer codigoGeometrista;
    private Double produtoBruto;
    private Double produtoDesconto;
    private Double produtoLiquido;
    private Double servicoBruto;
    private Double servicoDesconto;
    private Double servicoLiquido;
    private Double lubrificanteBruto;
    private Double lubrificanteDesconto;
    private Double lubrificanteLiquido;

    @Size(max = 50)
    private String codigoSmv;
    @Size(max = 50)
    private String codigoEmpenho;
    @Size(max = 50)
    private String codigoOrcamento;

    private Double pecaParalelaBruta;
    private Double pecaParalelaDesconto;
    private Double pecaParalelaLiquida;
    private Double totalDeslocamento;
}
