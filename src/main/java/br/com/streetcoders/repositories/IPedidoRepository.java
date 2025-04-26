package br.com.streetcoders.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.streetcoders.models.Pedido;

@Repository
public interface IPedidoRepository extends JpaRepository<Pedido, Long> {

}
