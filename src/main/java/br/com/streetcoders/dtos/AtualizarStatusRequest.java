package br.com.streetcoders.dtos;

import br.com.streetcoders.models.Pedido.PedidoStatus;
import lombok.Data;

@Data
public class AtualizarStatusRequest {
    private PedidoStatus status;
}
