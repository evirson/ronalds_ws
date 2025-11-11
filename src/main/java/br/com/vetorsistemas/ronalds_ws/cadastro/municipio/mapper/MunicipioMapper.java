package br.com.vetorsistemas.ronalds_ws.cadastro.municipio.mapper;

import br.com.vetorsistemas.ronalds_ws.cadastro.municipio.Municipio;
import br.com.vetorsistemas.ronalds_ws.cadastro.municipio.dto.MunicipioDto;
import org.springframework.stereotype.Component;

@Component
public class MunicipioMapper {
    public MunicipioDto toDTO(Municipio m) {
        if (m == null) return null;
        return MunicipioDto.builder()
                .id(m.getId())
                .codigoEstado(m.getCodigoEstado())
                .municipio(m.getMunicipio())
                .inicioCep(m.getInicioCep())
                .finalCep(m.getFinalCep())
                .build();
    }

    public Municipio fromDTO(MunicipioDto d) {
        if (d == null) return null;
        return Municipio.builder()
                .id(d.getId())
                .codigoEstado(d.getCodigoEstado())
                .municipio(d.getMunicipio())
                .inicioCep(d.getInicioCep())
                .finalCep(d.getFinalCep())
                .build();
    }

    public void updateEntityFromDTO(MunicipioDto d, Municipio e) {
        if (d == null || e == null) return;
        e.setCodigoEstado(d.getCodigoEstado());
        e.setMunicipio(d.getMunicipio());
        e.setInicioCep(d.getInicioCep());
        e.setFinalCep(d.getFinalCep());
    }
}

