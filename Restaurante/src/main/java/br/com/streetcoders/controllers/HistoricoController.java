package br.com.streetcoders.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.streetcoders.dtos.ListPedidoDto;
import br.com.streetcoders.models.Pedido.PedidoStatus;
import br.com.streetcoders.services.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/historico")
@Tag(name = "Historico", description = "Histórico de pedidos")
public class HistoricoController {
	
	private final PedidoService pedidoService;
	
	public HistoricoController(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}
	
	@GetMapping
	@Operation(
			summary = "Listar histórico de pedidos finalizados",
			responses = {
				@ApiResponse(responseCode = "200", description = "Lista de pedidos retornada"),
				@ApiResponse(responseCode = "404", description = "Nenhum pedido encontrado")
			}
		)
	public ResponseEntity<List<ListPedidoDto>> listarHistorico() {
		return ResponseEntity.ok(pedidoService.listarPedidosPorStatus(PedidoStatus.ENTREGUE));
	}
	
}
