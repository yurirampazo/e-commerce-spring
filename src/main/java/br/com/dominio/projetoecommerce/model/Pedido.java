package br.com.dominio.projetoecommerce.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private final LocalDateTime instante = LocalDateTime.now();

  @JsonIgnoreProperties("pedido")
  @JsonProperty("pagamento")
  @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
  private Pagamento pagamento;

  @JsonIgnoreProperties("pedidos")
  @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
  @JoinColumn(name = "cliente")
  private Cliente cliente;

  @ManyToOne
  @JoinColumn(name = "endereco_De_entrega_id")
  @JsonIgnoreProperties("pedidos")
  private Endereco enderecoDeEntrega;

  @JsonIgnore
  @OneToMany(mappedBy = "id.pedido")
  private Set<ItemPedido> itens = new HashSet<>();

  public Pedido(Integer id, Cliente cliente, Endereco enderecoDeEntrega) {
    this.id = id;
    this.cliente = cliente;
    this.enderecoDeEntrega = enderecoDeEntrega;
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

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Pedido Número: ");
    sb.append(getId());
    sb.append("\nInstante: ");
    String instant = getInstante().toString()
          .replaceAll("(\\d\\d\\d\\d)-(\\d\\d)-(\\d\\d)T(\\d\\d):(\\d\\d):(\\d\\d).\\d+",
                "Data: $3/$2/$1\nHorário: $4:$5:$6h");
    sb.append("\n"+instant);
    sb.append("\nCliente: ");
    sb.append(getCliente().getNome());
    sb.append("\nSituação Pagamento: ");
    sb.append(getPagamento().getEstadoPagamento().getDescricao());
    sb.append("\nDetalhes: ");
    getItens().forEach(x -> sb.append(x.toString()));
    sb.append("\nValor Total: ");
    sb.append(getValorTotal());
    sb.append("\n");
    return sb.toString();
  }
}
