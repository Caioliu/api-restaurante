package br.com.streetcoders.dtos;

import lombok.Data;

@Data
public class ItemPedidoRequest {
    private Long produtoId;
    private Integer quantidade;
}
