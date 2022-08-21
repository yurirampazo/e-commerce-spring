package br.com.dominio.projetoecommerce.repository;

import br.com.dominio.projetoecommerce.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
  Optional<Cliente> findClienteByCpfCnpjContainingIgnoreCase(String cpfCnpj);

  Optional<Cliente> findClienteByEmailContainingIgnoreCase(String email);
}
