package br.com.streetcoders.mappers;

import br.com.streetcoders.dtos.ProdutoDto;
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
public class IProdutoMapperImpl implements IProdutoMapper {

    @Override
    public ProdutoDto toDto(Produto produto) {
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

    @Override
    public Produto toEntity(ProdutoDto dto) {
        if ( dto == null ) {
            return null;
        }

        Produto.ProdutoBuilder produto = Produto.builder();

        produto.id( dto.getId() );
        produto.nome( dto.getNome() );
        produto.tipo( dto.getTipo() );
        produto.preco( dto.getPreco() );

        return produto.build();
    }

    @Override
    public List<ProdutoDto> toDtoList(List<Produto> produtos) {
        if ( produtos == null ) {
            return null;
        }

        List<ProdutoDto> list = new ArrayList<ProdutoDto>( produtos.size() );
        for ( Produto produto : produtos ) {
            list.add( toDto( produto ) );
        }

        return list;
    }

    @Override
    public List<Produto> toEntityList(List<ProdutoDto> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Produto> list = new ArrayList<Produto>( dtos.size() );
        for ( ProdutoDto produtoDto : dtos ) {
            list.add( toEntity( produtoDto ) );
        }

        return list;
    }
}
