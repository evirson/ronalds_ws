package br.com.vetorsistemas.ronalds_ws.ronalds_ws.mapper;

import br.com.vetorsistemas.ronalds_ws.ronalds_ws.dto.UsuarioDto;
import br.com.vetorsistemas.ronalds_ws.ronalds_ws.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UsuarioDto toDto(Usuario entity);

    Usuario toEntity(UsuarioDto dto);

    List<UsuarioDto> toDtoList(List<Usuario> entities);

    List<Usuario> toEntityList(List<UsuarioDto> dtos);

}