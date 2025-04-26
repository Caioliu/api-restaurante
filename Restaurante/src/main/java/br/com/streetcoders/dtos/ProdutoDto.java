package br.com.streetcoders.dtos;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.streetcoders.models.Produto.ProdutoTipo;
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
public class ProdutoDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long id;
    private String nome;
    private ProdutoTipo tipo;
    private BigDecimal preco;
}