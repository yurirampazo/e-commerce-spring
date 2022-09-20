package br.com.dominio.projetoecommerce.model;


import br.com.dominio.projetoecommerce.exception.MapToDtoException;
import br.com.dominio.projetoecommerce.model.dto.PagamentoDto;
import br.com.dominio.projetoecommerce.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@Entity
@Table(name = "pagamento")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento implements Serializable {
  
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
