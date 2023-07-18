package br.com.dominio.projetoecommerce.domain;


import br.com.dominio.projetoecommerce.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@Entity
@Table(name = "pagamento")
@JsonInclude
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public class Pagamento implements Serializable {

  private static final long serialVersionUID = 1L;
  
  @Id
  private Integer id;
  private Integer estadoPagamento;

  @OneToOne
  @JoinColumn(name = "pedido_id")
  @MapsId
  @NotNull
  @JsonIgnore
  private Pedido pedido;

  public Pagamento(Integer id, EstadoPagamento estadoPagamento, Pedido pedido) {
    this.id = id;
    this.estadoPagamento = estadoPagamento.getEstado();
    this.pedido = pedido;
  }

  public Integer getId() {
    return id;
  }

  public EstadoPagamento getEstadoPagamento() {
    return EstadoPagamento.toEnum(estadoPagamento);
  }

  public Pedido getPedido() {
    return pedido;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setEstadoPagamento(EstadoPagamento estadoPagamento) {
    this.estadoPagamento = estadoPagamento.getEstado();
  }

  public void setPedido(Pedido pedido) {
    this.pedido = pedido;
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Pagamento pagamento = (Pagamento) o;
    return Objects.equals(id, pagamento.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
