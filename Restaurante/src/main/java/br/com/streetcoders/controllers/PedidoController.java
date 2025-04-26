package br.com.streetcoders.controllers;

import br.com.streetcoders.dtos.*;
import br.com.streetcoders.models.Pedido.PedidoStatus;
import br.com.streetcoders.services.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@Tag(name = "Pedidos", description = "Gerenciamento de pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @Operation(
        summary = "Criar novo pedido",
        responses = {
            @ApiResponse(responseCode = "200", description = "Pedido criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro ao criar pedido")
        }
    )
    public ResponseEntity<PedidoDto> criarPedido(@RequestBody CriarPedidoDto dto) {
        PedidoDto pedido = pedidoService.criarPedido(dto.getClienteNome(), dto.getMesa());
        return ResponseEntity.ok(pedido);
    }

    @PostMapping("/{pedidoId}/produtos")
    @Operation(
        summary = "Adicionar produto ao pedido",
        responses = {
            @ApiResponse(responseCode = "200", description = "Produto adicionado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido ou produto não encontrado"),
            @ApiResponse(responseCode = "400", description = "Erro ao adicionar produto")
        }
    )
    public ResponseEntity<Void> adicionarProduto(
        @PathVariable Long pedidoId,
        @RequestBody ItemPedidoRequest request
    ) {
        pedidoService.adicionarProdutoAoPedido(pedidoId, request.getProdutoId(), request.getQuantidade());
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{pedidoId}/status")
    @Operation(
        summary = "Atualizar status do pedido",
        responses = {
            @ApiResponse(responseCode = "200", description = "Status atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
        }
    )
    
    public ResponseEntity<Void> atualizarStatus(
        @PathVariable Long pedidoId,
        @RequestParam PedidoStatus status
    ) {
        pedidoService.atualizarStatusPedido(pedidoId, status);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{pedidoId}/produtos/{produtoId}")
    @Operation(
        summary = "Remover produto do pedido",
        responses = {
            @ApiResponse(responseCode = "204", description = "Produto removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido ou produto não encontrado")
        }
    )
    public ResponseEntity<Void> removerProduto(
        @PathVariable Long pedidoId,
        @PathVariable Long produtoId,
        @RequestParam int quantidade
    ) {
        pedidoService.removerProdutoDoPedido(pedidoId, produtoId, quantidade);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{pedidoId}/produtos")
    @Operation(
        summary = "Remover todos os produtos do pedido",
        responses = {
            @ApiResponse(responseCode = "204", description = "Produtos removidos com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
        }
    )
    public ResponseEntity<Void> removerTodosProdutos(@PathVariable Long pedidoId) {
        pedidoService.removerTodosProdutosDoPedido(pedidoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{pedidoId}/total")
    @Operation(
        summary = "Calcular valor total do pedido",
        responses = {
            @ApiResponse(responseCode = "200", description = "Valor total calculado"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
        }
    )
    public ResponseEntity<PedidoValorTotalDto> calcularTotal(@PathVariable Long pedidoId) {
        BigDecimal result = pedidoService.calcularValorTotalPedido(pedidoId);
        return ResponseEntity.ok(new PedidoValorTotalDto(result));
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Buscar pedido por ID",
        responses = {
            @ApiResponse(responseCode = "200", description = "Pedido encontrado"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
        }
    )
    public ResponseEntity<PedidoDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.buscarPedidoPorId(id));
    }

    @GetMapping
    @Operation(
        summary = "Listar todos os pedidos",
        responses = {
            @ApiResponse(responseCode = "200", description = "Lista de pedidos retornada")
        }
    )
    public ResponseEntity<List<ListPedidoDto>> listarTodos() {
        return ResponseEntity.ok(pedidoService.listarTodosPedidos());
    }
}