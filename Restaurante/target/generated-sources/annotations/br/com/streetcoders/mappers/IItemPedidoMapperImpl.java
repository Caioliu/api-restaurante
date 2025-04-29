package br.com.streetcoders.mappers;

import br.com.streetcoders.dtos.ItemPedidoDto;
import br.com.streetcoders.dtos.ProdutoDto;
import br.com.streetcoders.models.ItemPedido;
import br.com.streetcoders.models.Produto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-04-28T22:36:42-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Eclipse Adoptium)"
)
@Component
public class IItemPedidoMapperImpl implements IItemPedidoMapper {

    @Override
    public ItemPedidoDto toDto(ItemPedido itemPedido) {
        if ( itemPedido == null ) {
            return null;
        }

        ItemPedidoDto itemPedidoDto = new ItemPedidoDto();

        itemPedidoDto.setProduto( produtoToProdutoDto( itemPedido.getProduto() ) );
        itemPedidoDto.setQuantidade( itemPedido.getQuantidade() );
        itemPedidoDto.setValorItens( itemPedido.getValorItens() );

        return itemPedidoDto;
    }

    @Override
    public ItemPedido toEntity(ItemPedidoDto dto) {
        if ( dto == null ) {
            return null;
        }

        ItemPedido.ItemPedidoBuilder itemPedido = ItemPedido.builder();

        itemPedido.produto( produtoDtoToProduto( dto.getProduto() ) );
        itemPedido.quantidade( dto.getQuantidade() );
        itemPedido.valorItens( dto.getValorItens() );

        return itemPedido.build();
    }

    @Override
    public List<ItemPedidoDto> toDtoList(List<ItemPedido> itemPedidos) {
        if ( itemPedidos == null ) {
            return null;
        }

        List<ItemPedidoDto> list = new ArrayList<ItemPedidoDto>( itemPedidos.size() );
        for ( ItemPedido itemPedido : itemPedidos ) {
            list.add( toDto( itemPedido ) );
        }

        return list;
    }

    @Override
    public List<ItemPedido> toEntityList(List<ItemPedidoDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<ItemPedido> list = new ArrayList<ItemPedido>( dtos.size() );
        for ( ItemPedidoDto itemPedidoDto : dtos ) {
            list.add( toEntity( itemPedidoDto ) );
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
}
