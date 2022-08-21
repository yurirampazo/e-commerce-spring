package br.com.dominio.projetoecommerce.repository;

import br.com.dominio.projetoecommerce.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
  Optional<Produto> findProdutoByNomeContainingIgnoreCase(String nome);
}
