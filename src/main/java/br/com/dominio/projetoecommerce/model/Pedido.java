package br.com.dominio.projetoecommerce.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "pedido")
@Getter
@NoArgsConstructor
public class Pedido implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String clienteNome;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private final LocalDateTime instante = LocalDateTime.now();

  @JsonIgnore
  @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
  private Pagamento pagamento;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cliente")
  private Cliente cliente;

  @ManyToOne
  @JoinColumn(name = "endereco_De_entrega_id")
  private Endereco enderecoDeEntrega;

  @JsonIgnore
  @OneToMany(mappedBy = "id.pedido")
  private Set<ItemPedido> itens = new HashSet<>();

  public Pedido(Integer id, Cliente cliente, Endereco enderecoDeEntrega) {
    this.id = id;
    this.cliente = cliente;
    this.enderecoDeEntrega = enderecoDeEntrega;
    this.clienteNome = cliente.getNome();
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setPagamento(Pagamento pagamento) {
    this.pagamento = pagamento;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
    this.enderecoDeEntrega = enderecoDeEntrega;
  }

  public void addItens(ItemPedido itemPedido) {
    itens.add(itemPedido);
  }

  public BigDecimal getValorTotal() {
    BigDecimal soma = BigDecimal.valueOf(0.0);
    for (ItemPedido ip : itens) {
      soma = soma.add(ip.getSubtotal());
    }
    return soma;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Pedido pedido = (Pedido) o;
    return Objects.equals(id, pedido.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
