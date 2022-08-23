package br.com.dominio.projetoecommerce.model;

import br.com.dominio.projetoecommerce.util.EstadoPagamento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class PagamentoComCartao extends Pagamento {
  private Integer numeroDeparcelas;

  public PagamentoComCartao(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Integer numeroDeparcelas) {
    super(id, estadoPagamento, pedido);
    this.numeroDeparcelas = numeroDeparcelas;
  }
}
