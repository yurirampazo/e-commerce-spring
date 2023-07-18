package br.com.dominio.projetoecommerce.repository;

import br.com.dominio.projetoecommerce.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
