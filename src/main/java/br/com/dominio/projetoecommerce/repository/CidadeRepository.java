package br.com.dominio.projetoecommerce.repository;

import br.com.dominio.projetoecommerce.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
  Optional<Cidade> findCidadeByNomeContainingIgnoreCase(String nome);
}
