package br.com.vetorsistemas.ronalds_ws.cadastro.fabrica.mapper;

import br.com.vetorsistemas.ronalds_ws.cadastro.fabrica.Fabrica;
import br.com.vetorsistemas.ronalds_ws.cadastro.fabrica.dto.FabricaDto;
import org.springframework.stereotype.Component;

@Component
public class FabricaMapper {
    public FabricaDto toDTO(Fabrica f) {
        if (f == null) return null;
        return FabricaDto.builder()
                .id(f.getId())
                .nomeFabricante(f.getNomeFabricante())
                .build();
    }

    public Fabrica fromDTO(FabricaDto d) {
        if (d == null) return null;
        return Fabrica.builder()
                .id(d.getId())
                .nomeFabricante(d.getNomeFabricante())
                .build();
    }

    public void updateEntityFromDTO(FabricaDto d, Fabrica e) {
        if (d == null || e == null) return;
        e.setNomeFabricante(d.getNomeFabricante());
    }
}

