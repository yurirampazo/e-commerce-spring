package br.com.dominio.projetoecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "item_pedido")
public class ItemPedido implements Serializable {

  private static final long serialVersionUID = 1L;

  @EmbeddedId
  private ItemPedidoPK id = new ItemPedidoPK();

  @Min(0)
  private BigDecimal desconto;

  @Min(1)
  @NotNull
  private Integer quantidade;

  @NotNull
  @Min(0)
  private BigDecimal preco;

  public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
    id.setPedido(pedido);
    id.setProduto(produto);
    this.desconto = BigDecimal.valueOf(desconto);
    this.quantidade = quantidade;
    this.preco = BigDecimal.valueOf(preco);
  }

  public Pedido getPedido() {
    return id.getPedido();
  }

  @JsonIgnore
  Produto getProduto() {
    return id.getProduto();
  }

  public void setId(ItemPedidoPK id) {
    this.id = id;
  }

  public void setDesconto(Double desconto) {
    this.desconto = BigDecimal.valueOf(desconto);
  }

  public void setQuantidade(Integer quantidade) {
    this.quantidade = quantidade;
  }

  public void setPreco(Double preco) {
    this.preco = BigDecimal.valueOf(preco);
  }

  public BigDecimal getSubtotal() {
    return (preco.subtract(desconto)).multiply(BigDecimal.valueOf(quantidade));
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
