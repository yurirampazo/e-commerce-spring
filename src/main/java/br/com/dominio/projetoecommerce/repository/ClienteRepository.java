package br.com.dominio.projetoecommerce.repository;

import br.com.dominio.projetoecommerce.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
  @Transactional(readOnly = true)
  Cliente findClienteByCpfCnpjContainingIgnoreCase(String cpfCnpj);

  @Transactional(readOnly = true)  //Para consultas não é envolvida como transação de banco de dados. Fica mais rápida
  Cliente findByEmail(String email);
}
