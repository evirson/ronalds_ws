package br.com.vetorsistemas.ronalds_ws.cadastro.estado.mapper;

import br.com.vetorsistemas.ronalds_ws.cadastro.estado.Estado;
import br.com.vetorsistemas.ronalds_ws.cadastro.estado.dto.EstadoCreateUpdateDTO;
import br.com.vetorsistemas.ronalds_ws.cadastro.estado.dto.EstadoDTO;
import org.springframework.stereotype.Component;

@Component
public class EstadoMapper {

    public EstadoDTO toDTO(Estado estado) {
        if (estado == null) return null;

        EstadoDTO dto = EstadoDTO.builder()
                .id(estado.getId())
                .sigla(estado.getSigla())
                .nome(estado.getNome())
                .codigoPais(estado.getCodigoPais())
                .build();

        // Adicionar nome do país se disponível
        if (estado.getPais() != null) {
            dto.setNomePais(estado.getPais().getNome());
        }

        return dto;
    }

    public Estado fromCreateUpdateDTO(EstadoCreateUpdateDTO dto) {
        if (dto == null) return null;

        return Estado.builder()
                .sigla(dto.getSigla())
                .nome(dto.getNome())
                .codigoPais(dto.getCodigoPais())
                .build();
    }

    public void updateEntityFromDTO(EstadoCreateUpdateDTO dto, Estado entity) {
        if (dto == null || entity == null) return;
        entity.setSigla(dto.getSigla());
        entity.setNome(dto.getNome());
        entity.setCodigoPais(dto.getCodigoPais());
    }
}
