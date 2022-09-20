package br.com.dominio.projetoecommerce.repository;

import br.com.dominio.projetoecommerce.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
  Optional<Cliente> findClienteByCpfCnpjContainingIgnoreCase(String cpfCnpj);

  @Transactional(readOnly = true)  //Para consultas não é envolvida como transação de banco de dados. Fica mais rápida
  Optional<Cliente> findClienteByEmailContainingIgnoreCase(String email);
}
