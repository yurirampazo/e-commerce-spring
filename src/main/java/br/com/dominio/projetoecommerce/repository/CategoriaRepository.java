package br.com.dominio.projetoecommerce.repository;

import br.com.dominio.projetoecommerce.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

  Optional<Categoria> findCategoriaByNomeContainingIgnoreCase(String nome);
}
