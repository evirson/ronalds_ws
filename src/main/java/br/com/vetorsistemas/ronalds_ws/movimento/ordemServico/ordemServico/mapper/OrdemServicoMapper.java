package br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.ordemServico.mapper;

import br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.ordemServico.OrdemServico;
import br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.ordemServico.dto.OrdemServicoCreateUpdateDTO;
import br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.ordemServico.dto.OrdemServicoDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrdemServicoMapper {

    public OrdemServicoDTO toDTO(OrdemServico os) {
        if (os == null) return null;

        OrdemServicoDTO dto = OrdemServicoDTO.builder()
                .id(os.getId())
                .tipoServico(os.getTipoServico())
                .dataAbertura(os.getDataAbertura())
                .horaAbertura(os.getHoraAbertura())
                .responsavel(os.getResponsavel())
                .codigoFuncionario(os.getCodigoFuncionario())
                .codigoCliente(os.getCodigoCliente())
                .codigoVeiculo(os.getCodigoVeiculo())
                .codigoCondicaoPagamento(os.getCodigoCondicaoPagamento())
                .horaFinalizacao(os.getHoraFinalizacao())
                .dataFinalizacao(os.getDataFinalizacao())
                .garantiaPecasMeses(os.getGarantiaPecasMeses())
                .garantiaMaoObraMeses(os.getGarantiaMaoObraMeses())
                .servicoLiberado(os.getServicoLiberado())
                .totalBruto(os.getTotalBruto())
                .valorDesconto(os.getValorDesconto())
                .totalLiquido(os.getTotalLiquido())
                .faturamento(os.getFaturamento())
                .dataFaturamento(os.getDataFaturamento())
                .numeroNotaFiscalServico(os.getNumeroNotaFiscalServico())
                .numeroNotaFiscalProduto(os.getNumeroNotaFiscalProduto())
                .observacoes(os.getObservacoes())
                .codigoGeometrista(os.getCodigoGeometrista())
                .produtoBruto(toDouble(os.getProdutoBruto()))
                .produtoDesconto(toDouble(os.getProdutoDesconto()))
                .produtoLiquido(toDouble(os.getProdutoLiquido()))
                .servicoBruto(toDouble(os.getServicoBruto()))
                .servicoDesconto(toDouble(os.getServicoDesconto()))
                .servicoLiquido(toDouble(os.getServicoLiquido()))
                .lubrificanteBruto(toDouble(os.getLubrificanteBruto()))
                .lubrificanteDesconto(toDouble(os.getLubrificanteDesconto()))
                .lubrificanteLiquido(toDouble(os.getLubrificanteLiquido()))
                .codigoSmv(os.getCodigoSmv())
                .codigoEmpenho(os.getCodigoEmpenho())
                .codigoOrcamento(os.getCodigoOrcamento())
                .pecaParalelaBruta(toDouble(os.getPecaParalelaBruta()))
                .pecaParalelaDesconto(toDouble(os.getPecaParalelaDesconto()))
                .pecaParalelaLiquida(toDouble(os.getPecaParalelaLiquida()))
                .totalDeslocamento(os.getTotalDeslocamento())
                .build();

        // Preencher dados relacionados
        if (os.getFuncionario() != null) {
            dto.setNomeFuncionario(os.getFuncionario().getNome());
        }

        if (os.getCliente() != null) {
            dto.setNomeCliente(os.getCliente().getNomeRazaoSocial());
        }

        if (os.getVeiculo() != null) {
            dto.setVeiculoPlaca(os.getVeiculo().getPlaca());
            dto.setVeiculoModelo(os.getVeiculo().getModelo());
            dto.setVeiculoTipoCarro(os.getVeiculo().getTipoCarro());
        }

        return dto;
    }

    public OrdemServico fromCreateUpdateDTO(OrdemServicoCreateUpdateDTO dto) {
        if (dto == null) return null;

        return OrdemServico.builder()
                .tipoServico(dto.getTipoServico())
                .dataAbertura(dto.getDataAbertura())
                .horaAbertura(dto.getHoraAbertura())
                .responsavel(dto.getResponsavel())
                .codigoFuncionario(dto.getCodigoFuncionario())
                .codigoCliente(dto.getCodigoCliente())
                .codigoVeiculo(dto.getCodigoVeiculo())
                .codigoCondicaoPagamento(dto.getCodigoCondicaoPagamento())
                .horaFinalizacao(dto.getHoraFinalizacao())
                .dataFinalizacao(dto.getDataFinalizacao())
                .garantiaPecasMeses(dto.getGarantiaPecasMeses())
                .garantiaMaoObraMeses(dto.getGarantiaMaoObraMeses())
                .servicoLiberado(dto.getServicoLiberado())
                .totalBruto(dto.getTotalBruto())
                .valorDesconto(dto.getValorDesconto())
                .totalLiquido(dto.getTotalLiquido())
                .faturamento(dto.getFaturamento())
                .dataFaturamento(dto.getDataFaturamento())
                .numeroNotaFiscalServico(dto.getNumeroNotaFiscalServico())
                .numeroNotaFiscalProduto(dto.getNumeroNotaFiscalProduto())
                .observacoes(dto.getObservacoes())
                .codigoGeometrista(dto.getCodigoGeometrista())
                .produtoBruto(toBigDecimal(dto.getProdutoBruto()))
                .produtoDesconto(toBigDecimal(dto.getProdutoDesconto()))
                .produtoLiquido(toBigDecimal(dto.getProdutoLiquido()))
                .servicoBruto(toBigDecimal(dto.getServicoBruto()))
                .servicoDesconto(toBigDecimal(dto.getServicoDesconto()))
                .servicoLiquido(toBigDecimal(dto.getServicoLiquido()))
                .lubrificanteBruto(toBigDecimal(dto.getLubrificanteBruto()))
                .lubrificanteDesconto(toBigDecimal(dto.getLubrificanteDesconto()))
                .lubrificanteLiquido(toBigDecimal(dto.getLubrificanteLiquido()))
                .codigoSmv(dto.getCodigoSmv())
                .codigoEmpenho(dto.getCodigoEmpenho())
                .codigoOrcamento(dto.getCodigoOrcamento())
                .pecaParalelaBruta(toBigDecimal(dto.getPecaParalelaBruta()))
                .pecaParalelaDesconto(toBigDecimal(dto.getPecaParalelaDesconto()))
                .pecaParalelaLiquida(toBigDecimal(dto.getPecaParalelaLiquida()))
                .totalDeslocamento(dto.getTotalDeslocamento())
                .build();
    }

    public void updateEntityFromDTO(OrdemServicoCreateUpdateDTO dto, OrdemServico entity) {
        if (dto == null || entity == null) return;

        entity.setTipoServico(dto.getTipoServico());
        entity.setDataAbertura(dto.getDataAbertura());
        entity.setHoraAbertura(dto.getHoraAbertura());
        entity.setResponsavel(dto.getResponsavel());
        entity.setCodigoFuncionario(dto.getCodigoFuncionario());
        entity.setCodigoCliente(dto.getCodigoCliente());
        entity.setCodigoVeiculo(dto.getCodigoVeiculo());
        entity.setCodigoCondicaoPagamento(dto.getCodigoCondicaoPagamento());
        entity.setHoraFinalizacao(dto.getHoraFinalizacao());
        entity.setDataFinalizacao(dto.getDataFinalizacao());
        entity.setGarantiaPecasMeses(dto.getGarantiaPecasMeses());
        entity.setGarantiaMaoObraMeses(dto.getGarantiaMaoObraMeses());
        entity.setServicoLiberado(dto.getServicoLiberado());
        entity.setTotalBruto(dto.getTotalBruto());
        entity.setValorDesconto(dto.getValorDesconto());
        entity.setTotalLiquido(dto.getTotalLiquido());
        entity.setFaturamento(dto.getFaturamento());
        entity.setDataFaturamento(dto.getDataFaturamento());
        entity.setNumeroNotaFiscalServico(dto.getNumeroNotaFiscalServico());
        entity.setNumeroNotaFiscalProduto(dto.getNumeroNotaFiscalProduto());
        entity.setObservacoes(dto.getObservacoes());
        entity.setCodigoGeometrista(dto.getCodigoGeometrista());
        entity.setProdutoBruto(toBigDecimal(dto.getProdutoBruto()));
        entity.setProdutoDesconto(toBigDecimal(dto.getProdutoDesconto()));
        entity.setProdutoLiquido(toBigDecimal(dto.getProdutoLiquido()));
        entity.setServicoBruto(toBigDecimal(dto.getServicoBruto()));
        entity.setServicoDesconto(toBigDecimal(dto.getServicoDesconto()));
        entity.setServicoLiquido(toBigDecimal(dto.getServicoLiquido()));
        entity.setLubrificanteBruto(toBigDecimal(dto.getLubrificanteBruto()));
        entity.setLubrificanteDesconto(toBigDecimal(dto.getLubrificanteDesconto()));
        entity.setLubrificanteLiquido(toBigDecimal(dto.getLubrificanteLiquido()));
        entity.setCodigoSmv(dto.getCodigoSmv());
        entity.setCodigoEmpenho(dto.getCodigoEmpenho());
        entity.setCodigoOrcamento(dto.getCodigoOrcamento());
        entity.setPecaParalelaBruta(toBigDecimal(dto.getPecaParalelaBruta()));
        entity.setPecaParalelaDesconto(toBigDecimal(dto.getPecaParalelaDesconto()));
        entity.setPecaParalelaLiquida(toBigDecimal(dto.getPecaParalelaLiquida()));
        entity.setTotalDeslocamento(dto.getTotalDeslocamento());
    }

    private Double toDouble(BigDecimal value) {
        return value != null ? value.doubleValue() : null;
    }

    private BigDecimal toBigDecimal(Double value) {
        return value != null ? BigDecimal.valueOf(value) : null;
    }
}
