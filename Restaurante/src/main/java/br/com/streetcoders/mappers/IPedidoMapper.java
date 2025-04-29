package br.com.streetcoders.mappers;

import java.util.List;
import org.mapstruct.Mapper;

import br.com.streetcoders.dtos.ListPedidoDto;
import br.com.streetcoders.dtos.PedidoDto;
import br.com.streetcoders.models.Pedido;

@Mapper(componentModel = "spring")
public interface IPedidoMapper {
	PedidoDto toDto(Pedido pedido);
	Pedido toEntity(PedidoDto dto);
	
	List<PedidoDto> toDetailedListDto(List<Pedido> pedidos);
	List<Pedido> toDetailedListEntity(List<PedidoDto> dtos);
	
	List<ListPedidoDto> toListDto(List<Pedido> pedidos);
	List<Pedido> toListEntity(List<ListPedidoDto> dtos);
}
