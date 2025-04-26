package br.com.streetcoders.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.streetcoders.models.Produto;

@Repository
public interface IProdutoRepository extends JpaRepository<Produto, Long> {

}
