package br.com.streetcoders.dtos;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.streetcoders.models.Pedido.PedidoStatus;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private List<ItemPedidoDto> itens;
    private String mesa;
    private String clienteNome;
    private Date dataHora;
    private PedidoStatus status;
    private BigDecimal valorTotal;
}
