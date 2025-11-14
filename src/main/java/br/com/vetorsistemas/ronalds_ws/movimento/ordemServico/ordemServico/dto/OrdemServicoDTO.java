package br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.ordemServico.dto;

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
public class OrdemServicoDTO {

    private Integer id;
    private String tipoServico;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataAbertura;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime horaAbertura;

    private String responsavel;
    private Integer codigoFuncionario;
    private String nomeFuncionario;

    private Integer codigoCliente;
    private String nomeCliente;

    private Integer codigoVeiculo;
    private String veiculoPlaca;
    private String veiculoModelo;
    private String veiculoTipoCarro;

    private Integer codigoCondicaoPagamento;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime horaFinalizacao;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataFinalizacao;

    private Integer garantiaPecasMeses;
    private Integer garantiaMaoObraMeses;
    private String servicoLiberado;
    private Double totalBruto;
    private Double valorDesconto;
    private Double totalLiquido;
    private String faturamento;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataFaturamento;

    private String numeroNotaFiscalServico;
    private String numeroNotaFiscalProduto;
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
    private String codigoSmv;
    private String codigoEmpenho;
    private String codigoOrcamento;
    private Double pecaParalelaBruta;
    private Double pecaParalelaDesconto;
    private Double pecaParalelaLiquida;
    private Double totalDeslocamento;
}
