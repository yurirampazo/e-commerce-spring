package br.com.dominio.projetoecommerce.domain;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class ItemPedidoPK implements Serializable {

  private static final long serialVersionUID = 1L;

  @ManyToOne()
  @JoinColumn(name = "pedido_id")
  private Pedido pedido;

  @ManyToOne()
  @JoinColumn(name = "produto_id")
  private Produto produto;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ItemPedidoPK that = (ItemPedidoPK) o;
    return Objects.equals(pedido, that.pedido) && Objects.equals(produto, that.produto);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pedido, produto);
  }
}
