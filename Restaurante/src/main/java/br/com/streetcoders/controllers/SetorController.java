package br.com.streetcoders.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.streetcoders.dtos.PedidoDto;
import br.com.streetcoders.models.Pedido.PedidoStatus;
import br.com.streetcoders.models.Produto.ProdutoTipo;
import br.com.streetcoders.services.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/setores")
@Tag(name = "Setores", description = "Visualização dos sistema por perspectiva de setores")
public class SetorController {

	private final PedidoService pedidoService;
	
	public SetorController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}
	
	@GetMapping("/cozinha/pedidos")
	@Operation(
	        summary = "Listar pedidos da cozinha",
	        responses = {
	            @ApiResponse(responseCode = "200", description = "Lista de pedidos retornada"),
	            @ApiResponse(responseCode = "404", description = "Nenhum pedido encontrado")
	        }
	    )
	public ResponseEntity<List<PedidoDto>> getPedidosCozinha() {
	    
	    return ResponseEntity.ok(
	        pedidoService.listarPedidosPorTipoEStatus(ProdutoTipo.PRATO)
	    );
	}
	
	@PatchMapping("/pedido/{id}/status")
    @Operation(
        summary = "Atualizar status do pedido",
        responses = {
            @ApiResponse(responseCode = "200", description = "Status atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado")
        }
    )
    
    public ResponseEntity<Void> atualizarStatus(
        @PathVariable Long id,
        @RequestParam PedidoStatus status
    ) {
        pedidoService.atualizarStatusPedido(id, status);
        return ResponseEntity.ok().build();
    }

	@GetMapping("/copa/pedidos")
	@Operation(
	        summary = "Listar pedidos da copa",
	        responses = {
	            @ApiResponse(responseCode = "200", description = "Lista de pedidos retornada"),
	            @ApiResponse(responseCode = "404", description = "Nenhum pedido encontrado")
	        }
	    )
	public ResponseEntity<List<PedidoDto>> getPedidosCopa() {
		
	    return ResponseEntity.ok(
	        pedidoService.listarPedidosPorTipoEStatus(ProdutoTipo.BEBIDA)
	    );
	}
 
}
