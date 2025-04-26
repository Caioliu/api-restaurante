package br.com.streetcoders.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import br.com.streetcoders.dtos.ProdutoDto;
import br.com.streetcoders.models.Produto;

@Mapper(componentModel = "spring")
public interface IProdutoMapper {
	ProdutoDto toDto(Produto produto);
	Produto toEntity(ProdutoDto dto);
	List<ProdutoDto> toDtoList(List<Produto> produtos);
	List<Produto> toEntityList(List<ProdutoDto> dtos);
}
