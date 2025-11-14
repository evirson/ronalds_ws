package br.com.vetorsistemas.ronalds_ws.cadastro.pais.mapper;

import br.com.vetorsistemas.ronalds_ws.cadastro.pais.Pais;
import br.com.vetorsistemas.ronalds_ws.cadastro.pais.dto.PaisCreateUpdateDTO;
import br.com.vetorsistemas.ronalds_ws.cadastro.pais.dto.PaisDTO;
import org.springframework.stereotype.Component;

@Component
public class PaisMapper {

    public PaisDTO toDTO(Pais pais) {
        if (pais == null) return null;

        return PaisDTO.builder()
                .id(pais.getId())
                .nome(pais.getNome())
                .build();
    }

    public Pais fromCreateUpdateDTO(PaisCreateUpdateDTO dto) {
        if (dto == null) return null;

        return Pais.builder()
                .nome(dto.getNome())
                .build();
    }

    public void updateEntityFromDTO(PaisCreateUpdateDTO dto, Pais entity) {
        if (dto == null || entity == null) return;
        entity.setNome(dto.getNome());
    }
}
