package br.com.dominio.projetoecommerce.repository;

import br.com.dominio.projetoecommerce.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
}
