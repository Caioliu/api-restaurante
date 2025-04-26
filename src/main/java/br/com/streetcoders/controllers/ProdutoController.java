package br.com.streetcoders.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.streetcoders.dtos.ProdutoDto;
import br.com.streetcoders.services.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/produtos")
@Tag(name = "Produtos", description = "Gerenciamento de produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar produto por ID",
            responses = {
                @ApiResponse(responseCode = "200", description = "Produto encontrado"),
                @ApiResponse(responseCode = "404", description = "Produto não encontrado")
            }
        )
    public ResponseEntity<ProdutoDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarProdutoPorId(id));
    }

    @GetMapping
    @Operation(
			summary = "Listar todos os produtos",
			responses = {
				@ApiResponse(responseCode = "200", description = "Lista de produtos retornada")
			}
		)
    public ResponseEntity<List<ProdutoDto>> listarTodos() {
        return ResponseEntity.ok((List<ProdutoDto>) produtoService.listarTodosProdutos());
    }

    @PostMapping
    @Operation(
			summary = "Criar novo produto",
			responses = {
				@ApiResponse(responseCode = "201", description = "Produto criado com sucesso"),
				@ApiResponse(responseCode = "400", description = "Erro ao criar produto")
			}
		)
    public ResponseEntity<ProdutoDto> criar(@RequestBody ProdutoDto produto) {
        return ResponseEntity.ok(produtoService.criarProduto(produto.getNome(),
        													 produto.getTipo(),
        													 produto.getPreco()));
    }

    @PutMapping("/{id}")
    @Operation(
    		summary = "Atualizar produto por ID",
    		responses = {
    			@ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
    			@ApiResponse(responseCode = "404", description = "Produto não encontrado"),
    		}
    	)
    public ResponseEntity<ProdutoDto> atualizar(@PathVariable Long id,
    										 @RequestBody ProdutoDto produto) {
        return ResponseEntity.ok(produtoService.atualizarProduto(id,
        														produto.getNome(),
        													    produto.getTipo(),
        													    produto.getPreco()));
    }

    @DeleteMapping("/{id}")
    @Operation(
    		summary = "Remover produto por ID",
    		responses = {
				@ApiResponse(responseCode = "204", description = "Produto removido com sucesso"),
				@ApiResponse(responseCode = "404", description = "Produto não encontrado"),
			}
        )	
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        produtoService.removerProduto(id);
        return ResponseEntity.noContent().build();
    }
}