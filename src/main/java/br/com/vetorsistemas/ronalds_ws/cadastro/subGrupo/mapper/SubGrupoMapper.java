package br.com.vetorsistemas.ronalds_ws.cadastro.subGrupo.mapper;

import br.com.vetorsistemas.ronalds_ws.cadastro.subGrupo.SubGrupo;
import br.com.vetorsistemas.ronalds_ws.cadastro.subGrupo.dto.SubGrupoDto;
import org.springframework.stereotype.Component;

@Component
public class SubGrupoMapper {
    public SubGrupoDto toDTO(SubGrupo s) {
        if (s == null) return null;
        return SubGrupoDto.builder()
                .id(s.getId())
                .nomeSubGrupo(s.getNomeSubGrupo())
                .build();
    }

    public SubGrupo fromDTO(SubGrupoDto d) {
        if (d == null) return null;
        return SubGrupo.builder()
                .id(d.getId())
                .nomeSubGrupo(d.getNomeSubGrupo())
                .build();
    }

    public void updateEntityFromDTO(SubGrupoDto d, SubGrupo e) {
        if (d == null || e == null) return;
        e.setNomeSubGrupo(d.getNomeSubGrupo());
    }
}

