package br.com.dominio.projetoecommerce.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Pedido implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDateTime instante;

  @JsonIgnore
  @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
  private Pagamento pagamento;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "cliente")
  private Cliente cliente;

  @ManyToOne
  @JoinColumn(name = "endereco_De_entrega_id")
  private Endereco enderecoDeEntrega;

  public Pedido(Integer id, LocalDateTime instante, Cliente cliente, Endereco enderecoDeEntrega) {
    this.id = id;
    this.instante = instante;
    this.cliente = cliente;
    this.enderecoDeEntrega = enderecoDeEntrega;
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
