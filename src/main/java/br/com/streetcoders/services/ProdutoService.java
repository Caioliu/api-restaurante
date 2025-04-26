package br.com.streetcoders.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.streetcoders.dtos.ProdutoDto;
import br.com.streetcoders.mappers.IProdutoMapper;
import br.com.streetcoders.models.Produto;
import br.com.streetcoders.models.Produto.ProdutoTipo;
import br.com.streetcoders.repositories.IProdutoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoService {
	
		private final IProdutoRepository produtoRepository;
	    private final IProdutoMapper produtoMapper; // Injeção do mapper
		
	    public ProdutoDto buscarProdutoPorId(Long id) {
	        Produto produto = produtoRepository.findById(id).orElseThrow();
	        return produtoMapper.toDto(produto);
	    }
	    
		public Iterable<ProdutoDto> listarTodosProdutos() {
			List<Produto> produtos = produtoRepository.findAll();
			return produtos.stream()
					.map(produtoMapper::toDto)
					.collect(Collectors.toList());
		}
		
		public ProdutoDto criarProduto(String nome, ProdutoTipo tipo, BigDecimal preco) {
			Produto produto = Produto.builder()
					.nome(nome)
					.tipo(tipo)
					.preco(preco)
					.build();
			return produtoMapper.toDto(produtoRepository.save(produto));
		}
		
		public ProdutoDto atualizarProduto(Long id, String nome, ProdutoTipo tipo, BigDecimal preco) {
			Produto produto = produtoRepository.findById(id).orElseThrow();
			produto.setNome(nome);
			produto.setTipo(tipo);
			produto.setPreco(preco);
			return produtoMapper.toDto(produtoRepository.save(produto));
		}
		
		public void removerProduto(Long id) {
			produtoRepository.deleteById(id);
		}

}
