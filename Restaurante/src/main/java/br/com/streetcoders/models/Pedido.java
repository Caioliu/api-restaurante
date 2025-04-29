package br.com.streetcoders.models;

import java.math.BigDecimal;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pedido")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

	public enum PedidoStatus {
		ABERTO, EMPREPARO, PRONTO, ENTREGUE;

		public static PedidoStatus getByName(String value) {
			for (PedidoStatus status : PedidoStatus.values()) {
	            if (status.name().equals(value)) {
	                return status;
	            }
	        }
			return null;
		}
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pedido_seq")
	@SequenceGenerator(name="pedido_seq", sequenceName="sq_pedido", initialValue = 1, allocationSize = 1)
	private Long id;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<ItemPedido> itens = new HashSet<>();
	
	@Column(name = "mesa", nullable = true, length = 20)
	private String mesa;
	
	@Column(name = "cliente_nome", nullable = false, length = 50)
	private String clienteNome;
	
	@Column(name = "data_hora", nullable = true)
	private Date dataHora;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private PedidoStatus status;
	
	public void adicionarProduto(Produto produto, Integer quantidade) {
        ItemPedido existingItem = findItemByProduto(produto);
        if (existingItem != null) {
            existingItem.setQuantidade(existingItem.getQuantidade() + quantidade);
        } else {
            ItemPedido newItem = ItemPedido.builder()
                    .pedido(this)
                    .produto(produto)
                    .quantidade(quantidade)
                    .precoUnitario(produto.getPreco())
                    .build();
            itens.add(newItem);
        }
    }

    public void removerProduto(Produto produto, Integer quantidade) {
        ItemPedido item = findItemByProduto(produto);
        if (item != null) {
            int novaQuantidade = item.getQuantidade() - quantidade;
            if (novaQuantidade <= 0) {
                itens.remove(item);
            } else {
                item.setQuantidade(novaQuantidade);
            }
        }
    }

    public void removerTodosProdutos() {
        itens.clear();
    }

    public int getQuantidadeTotalProdutos() {
        return itens.stream()
                .mapToInt(ItemPedido::getQuantidade)
                .sum();
    }

    public BigDecimal calcularValorTotalPedido() {
        return itens.stream()
                .map(ItemPedido::getValorItens)
                .filter(valor -> valor != null)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private ItemPedido findItemByProduto(Produto produto) {
        return itens.stream()
                .filter(item -> item.getProduto().getId().equals(produto.getId()))
                .findFirst()
                .orElse(null);
    }
}
