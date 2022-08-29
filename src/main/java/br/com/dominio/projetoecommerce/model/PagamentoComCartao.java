package br.com.dominio.projetoecommerce.model;

import br.com.dominio.projetoecommerce.util.EstadoPagamento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "pagamento_com_cartao")
public class PagamentoComCartao extends Pagamento {
  private Integer numeroDeparcelas;

  public PagamentoComCartao(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, Integer numeroDeparcelas) {
    super(id, estadoPagamento, pedido);
    this.numeroDeparcelas = numeroDeparcelas;
  }
}
