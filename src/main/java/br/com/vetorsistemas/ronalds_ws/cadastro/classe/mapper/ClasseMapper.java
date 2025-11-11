package br.com.vetorsistemas.ronalds_ws.cadastro.classe.mapper;

import br.com.vetorsistemas.ronalds_ws.cadastro.classe.Classe;
import br.com.vetorsistemas.ronalds_ws.cadastro.classe.dto.ClasseDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ClasseMapper {

    public Classe toEntity(ClasseDto dto) {
        if (dto == null) {
            return null;
        }


        Classe ent = new Classe();
        ent.setNomeClasse(dto.getNomeClasse());
        ent.setTipoClasse(dto.getTipoClasse());
        ent.setPercentualDescontoPadrao(dto.getPercentualDescontoPadrao());
        ent.setDataAlteracao(LocalDateTime.now());

        return ent;
    }

    public ClasseDto toDTO(Classe ent) {
        if (ent == null) {
            return null;
        }

        ClasseDto dto = new ClasseDto();
        dto.setId(ent.getId());
        dto.setNomeClasse(ent.getNomeClasse());
        dto.setTipoClasse(ent.getTipoClasse());
        dto.setPercentualDescontoPadrao(ent.getPercentualDescontoPadrao());

        return dto;
    }
}

