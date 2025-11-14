package br.com.vetorsistemas.ronalds_ws.cadastro.classificacaoFiscal.mapper;

import br.com.vetorsistemas.ronalds_ws.cadastro.classe.Classe;
import br.com.vetorsistemas.ronalds_ws.cadastro.classe.dto.ClasseDto;
import br.com.vetorsistemas.ronalds_ws.cadastro.classificacaoFiscal.ClassificacaoFiscal;
import br.com.vetorsistemas.ronalds_ws.cadastro.classificacaoFiscal.dto.ClassificacaoFiscalDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ClassificacaoFiscalMapper {

    public ClassificacaoFiscal toEntity(ClassificacaoFiscalDto dto) {
        if (dto == null) {
            return null;
        }


        ClassificacaoFiscal ent = new ClassificacaoFiscal();
        ent.setCodigoNcm(dto.getCodigoNcm());
        ent.setDescricaoNcm(dto.getDescricaoNcm());
        ent.setAliquotaIbpt(dto.getAliquotaIbpt());
        ent.setCodigoCest(dto.getCodigoCest());

        return ent;
    }

    public ClassificacaoFiscalDto toDTO(ClassificacaoFiscal ent) {
        if (ent == null) {
            return null;
        }

        ClassificacaoFiscalDto dto = new ClassificacaoFiscalDto();
        dto.setId(ent.getId());
        dto.setCodigoNcm(ent.getCodigoNcm());
        dto.setDescricaoNcm(ent.getDescricaoNcm());
        dto.setAliquotaIbpt(ent.getAliquotaIbpt());
        dto.setCodigoCest(ent.getCodigoCest());

        return dto;
    }
}

