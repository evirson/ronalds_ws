package br.com.vetorsistemas.ronalds_ws.cadastro.grupo.mapper;

import br.com.vetorsistemas.ronalds_ws.cadastro.grupo.Grupo;
import br.com.vetorsistemas.ronalds_ws.cadastro.grupo.dto.GrupoDto;
import org.springframework.stereotype.Component;

@Component
public class GrupoMapper {
    public GrupoDto toDTO(Grupo g) {
        if (g == null) return null;
        return GrupoDto.builder()
                .id(g.getId())
                .nomeGrupo(g.getNomeGrupo())
                .build();
    }

    public Grupo fromDTO(GrupoDto d) {
        if (d == null) return null;
        return Grupo.builder()
                .id(d.getId())
                .nomeGrupo(d.getNomeGrupo())
                .build();
    }

    public void updateEntityFromDTO(GrupoDto d, Grupo e) {
        if (d == null || e == null) return;
        e.setNomeGrupo(d.getNomeGrupo());
    }
}

