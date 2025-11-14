package br.com.vetorsistemas.ronalds_ws.movimento.detalhesCaixa.mapper;

import br.com.vetorsistemas.ronalds_ws.movimento.detalhesCaixa.DetalheCaixa;
import br.com.vetorsistemas.ronalds_ws.movimento.detalhesCaixa.dto.DetalheCaixaDto;
import org.springframework.stereotype.Component;

@Component
public class DetalheCaixaMapper {
    public DetalheCaixaDto toDTO(DetalheCaixa entity) {
        if (entity == null) return null;
        return DetalheCaixaDto.builder()
                .id(entity.getId())
                .planoContas(entity.getPlanoContas())
                .numeroDocumento(entity.getNumeroDocumento())
                .historico(entity.getHistorico())
                .dataMovimento(entity.getDataMovimento())
                .valorMovimento(entity.getValorMovimento())
                .debitoCredito(entity.getDebitoCredito())
                .codigoCaixa(entity.getCodigoCaixa())
                .status(entity.getStatus())
                .build();
    }

    public DetalheCaixa fromDTO(DetalheCaixaDto dto) {
        if (dto == null) return null;
        return DetalheCaixa.builder()
                .id(dto.getId())
                .planoContas(dto.getPlanoContas())
                .numeroDocumento(dto.getNumeroDocumento())
                .historico(dto.getHistorico())
                .dataMovimento(dto.getDataMovimento())
                .valorMovimento(dto.getValorMovimento())
                .debitoCredito(dto.getDebitoCredito())
                .build();
    }

    public void updateEntityFromDTO(DetalheCaixaDto dto, DetalheCaixa entity) {
        if (dto == null || entity == null) return;
        entity.setPlanoContas(dto.getPlanoContas());
        entity.setNumeroDocumento(dto.getNumeroDocumento());
        entity.setHistorico(dto.getHistorico());
        entity.setDataMovimento(dto.getDataMovimento());
        entity.setValorMovimento(dto.getValorMovimento());
        entity.setDebitoCredito(dto.getDebitoCredito());
    }
}
