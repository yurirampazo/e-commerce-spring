package br.com.dominio.projetoecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class ItemPedido implements Serializable {

  @EmbeddedId
  private ItemPedidoPK id = new ItemPedidoPK();

  private BigDecimal desconto;
  private Integer quantidade;
  private BigDecimal preco;

  public ItemPedido(Pedido pedido, Produto produto, BigDecimal desconto, Integer quantidade, BigDecimal preco) {
    id.setPedido(pedido);
    id.setProduto(produto);
    this.desconto = desconto;
    this.quantidade = quantidade;
    this.preco = preco;
  }

  public Pedido getPedido() {
    return id.getPedido();
  }

  @JsonIgnore
  Produto getProduto() {
    return id.getProduto();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ItemPedido that = (ItemPedido) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
