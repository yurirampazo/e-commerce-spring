package br.com.dominio.projetoecommerce.repository;

import br.com.dominio.projetoecommerce.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}
