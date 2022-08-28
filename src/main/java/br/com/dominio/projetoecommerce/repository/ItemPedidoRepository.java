package br.com.dominio.projetoecommerce.repository;

import br.com.dominio.projetoecommerce.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
}
