package br.com.streetcoders.mappers;

import br.com.streetcoders.dtos.ItemPedidoDto;
import br.com.streetcoders.dtos.ListPedidoDto;
import br.com.streetcoders.dtos.PedidoDto;
import br.com.streetcoders.dtos.ProdutoDto;
import br.com.streetcoders.models.ItemPedido;
import br.com.streetcoders.models.Pedido;
import br.com.streetcoders.models.Produto;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-29T12:08:28-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class IPedidoMapperImpl implements IPedidoMapper {

    @Override
    public PedidoDto toDto(Pedido pedido) {
        if ( pedido == null ) {
            return null;
        }

        PedidoDto pedidoDto = new PedidoDto();

        pedidoDto.setId( pedido.getId() );
        pedidoDto.setItens( itemPedidoSetToItemPedidoDtoList( pedido.getItens() ) );
        pedidoDto.setMesa( pedido.getMesa() );
        pedidoDto.setClienteNome( pedido.getClienteNome() );
        pedidoDto.setDataHora( pedido.getDataHora() );
        pedidoDto.setStatus( pedido.getStatus() );

        return pedidoDto;
    }

    @Override
    public Pedido toEntity(PedidoDto dto) {
        if ( dto == null ) {
            return null;
        }

        Pedido.PedidoBuilder pedido = Pedido.builder();

        pedido.id( dto.getId() );
        pedido.itens( itemPedidoDtoListToItemPedidoSet( dto.getItens() ) );
        pedido.mesa( dto.getMesa() );
        pedido.clienteNome( dto.getClienteNome() );
        pedido.dataHora( dto.getDataHora() );
        pedido.status( dto.getStatus() );

        return pedido.build();
    }

    @Override
    public List<PedidoDto> toDetailedListDto(List<Pedido> pedidos) {
        if ( pedidos == null ) {
            return null;
        }

        List<PedidoDto> list = new ArrayList<PedidoDto>( pedidos.size() );
        for ( Pedido pedido : pedidos ) {
            list.add( toDto( pedido ) );
        }

        return list;
    }

    @Override
    public List<Pedido> toDetailedListEntity(List<PedidoDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Pedido> list = new ArrayList<Pedido>( dtos.size() );
        for ( PedidoDto pedidoDto : dtos ) {
            list.add( toEntity( pedidoDto ) );
        }

        return list;
    }

    @Override
    public List<ListPedidoDto> toListDto(List<Pedido> pedidos) {
        if ( pedidos == null ) {
            return null;
        }

        List<ListPedidoDto> list = new ArrayList<ListPedidoDto>( pedidos.size() );
        for ( Pedido pedido : pedidos ) {
            list.add( pedidoToListPedidoDto( pedido ) );
        }

        return list;
    }

    @Override
    public List<Pedido> toListEntity(List<ListPedidoDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Pedido> list = new ArrayList<Pedido>( dtos.size() );
        for ( ListPedidoDto listPedidoDto : dtos ) {
            list.add( listPedidoDtoToPedido( listPedidoDto ) );
        }

        return list;
    }

    protected ProdutoDto produtoToProdutoDto(Produto produto) {
        if ( produto == null ) {
            return null;
        }

        ProdutoDto produtoDto = new ProdutoDto();

        produtoDto.setId( produto.getId() );
        produtoDto.setNome( produto.getNome() );
        produtoDto.setTipo( produto.getTipo() );
        produtoDto.setPreco( produto.getPreco() );

        return produtoDto;
    }

    protected ItemPedidoDto itemPedidoToItemPedidoDto(ItemPedido itemPedido) {
        if ( itemPedido == null ) {
            return null;
        }

        ItemPedidoDto itemPedidoDto = new ItemPedidoDto();

        itemPedidoDto.setProduto( produtoToProdutoDto( itemPedido.getProduto() ) );
        itemPedidoDto.setQuantidade( itemPedido.getQuantidade() );
        itemPedidoDto.setValorItens( itemPedido.getValorItens() );

        return itemPedidoDto;
    }

    protected List<ItemPedidoDto> itemPedidoSetToItemPedidoDtoList(Set<ItemPedido> set) {
        if ( set == null ) {
            return null;
        }

        List<ItemPedidoDto> list = new ArrayList<ItemPedidoDto>( set.size() );
        for ( ItemPedido itemPedido : set ) {
            list.add( itemPedidoToItemPedidoDto( itemPedido ) );
        }

        return list;
    }

    protected Produto produtoDtoToProduto(ProdutoDto produtoDto) {
        if ( produtoDto == null ) {
            return null;
        }

        Produto.ProdutoBuilder produto = Produto.builder();

        produto.id( produtoDto.getId() );
        produto.nome( produtoDto.getNome() );
        produto.tipo( produtoDto.getTipo() );
        produto.preco( produtoDto.getPreco() );

        return produto.build();
    }

    protected ItemPedido itemPedidoDtoToItemPedido(ItemPedidoDto itemPedidoDto) {
        if ( itemPedidoDto == null ) {
            return null;
        }

        ItemPedido.ItemPedidoBuilder itemPedido = ItemPedido.builder();

        itemPedido.produto( produtoDtoToProduto( itemPedidoDto.getProduto() ) );
        itemPedido.quantidade( itemPedidoDto.getQuantidade() );
        itemPedido.valorItens( itemPedidoDto.getValorItens() );

        return itemPedido.build();
    }

    protected Set<ItemPedido> itemPedidoDtoListToItemPedidoSet(List<ItemPedidoDto> list) {
        if ( list == null ) {
            return null;
        }

        Set<ItemPedido> set = new LinkedHashSet<ItemPedido>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( ItemPedidoDto itemPedidoDto : list ) {
            set.add( itemPedidoDtoToItemPedido( itemPedidoDto ) );
        }

        return set;
    }

    protected ListPedidoDto pedidoToListPedidoDto(Pedido pedido) {
        if ( pedido == null ) {
            return null;
        }

        ListPedidoDto listPedidoDto = new ListPedidoDto();

        listPedidoDto.setId( pedido.getId() );
        listPedidoDto.setMesa( pedido.getMesa() );
        listPedidoDto.setClienteNome( pedido.getClienteNome() );
        listPedidoDto.setDataHora( pedido.getDataHora() );
        listPedidoDto.setStatus( pedido.getStatus() );

        return listPedidoDto;
    }

    protected Pedido listPedidoDtoToPedido(ListPedidoDto listPedidoDto) {
        if ( listPedidoDto == null ) {
            return null;
        }

        Pedido.PedidoBuilder pedido = Pedido.builder();

        pedido.id( listPedidoDto.getId() );
        pedido.mesa( listPedidoDto.getMesa() );
        pedido.clienteNome( listPedidoDto.getClienteNome() );
        pedido.dataHora( listPedidoDto.getDataHora() );
        pedido.status( listPedidoDto.getStatus() );

        return pedido.build();
    }
}
