package br.com.streetcoders.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.streetcoders.dtos.UsuarioDto;
import br.com.streetcoders.models.Usuario;

@Mapper(componentModel = "spring")
public interface IUsuarioMapper {
	 UsuarioDto toDto(Usuario usuario);
	 @Mapping(target = "passwordHash", ignore = true)
	 Usuario toEntity(UsuarioDto dto);
	 List<UsuarioDto> toDtoList(List<Usuario> usuarios);
	 List<Usuario> toEntityList(List<UsuarioDto> dtos);
}
