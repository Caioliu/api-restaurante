package br.com.streetcoders.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.streetcoders.dtos.ItemPedidoDto;
import br.com.streetcoders.models.ItemPedido;

@Mapper(componentModel = "spring")
public interface IItemPedidoMapper {
	
	ItemPedidoDto toDto(ItemPedido itemPedido);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "pedido", ignore = true)
    @Mapping(target = "precoUnitario", ignore = true)
    ItemPedido toEntity(ItemPedidoDto dto);
    
    List<ItemPedidoDto> toDtoList(List<ItemPedido> itemPedidos);
    List<ItemPedido> toEntityList(List<ItemPedidoDto> dtos);
}