package br.com.dominio.projetoecommerce.repository;

import br.com.dominio.projetoecommerce.model.Cidade;
import br.com.dominio.projetoecommerce.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoRepository extends JpaRepository<Estado, Integer> {
  Optional<Cidade> findEstadoByNomeContainingIgnoreCase(String nome);
}
