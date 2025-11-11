package br.com.vetorsistemas.ronalds_ws.cadastro.planoContas.mapper;

import br.com.vetorsistemas.ronalds_ws.cadastro.planoContas.PlanoContas;
import br.com.vetorsistemas.ronalds_ws.cadastro.planoContas.dto.PlanoContasDto;
import org.springframework.stereotype.Component;

@Component
public class PlanoContasMapper {
    public PlanoContasDto toDTO(PlanoContas p) {
        if (p == null) return null;
        return PlanoContasDto.builder()
                .id(p.getId())
                .descricao(p.getDescricao())
                .dataCadastro(p.getDataCadastro())
                .dataAlteracao(p.getDataAlteracao())
                .build();
    }

    public PlanoContas fromDTO(PlanoContasDto d) {
        if (d == null) return null;
        return PlanoContas.builder()
                .id(d.getId())
                .descricao(d.getDescricao())
                .dataCadastro(d.getDataCadastro())
                .dataAlteracao(d.getDataAlteracao())
                .build();
    }

    public void updateEntityFromDTO(PlanoContasDto d, PlanoContas e) {
        if (d == null || e == null) return;
        e.setDescricao(d.getDescricao());
        e.setDataCadastro(d.getDataCadastro());
        e.setDataAlteracao(d.getDataAlteracao());
    }
}

