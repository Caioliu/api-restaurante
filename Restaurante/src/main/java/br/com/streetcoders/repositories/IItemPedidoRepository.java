package br.com.streetcoders.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.streetcoders.models.ItemPedido;

@Repository
public interface IItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
    // Consultas personalizadas podem ser adicionadas aqui
}
