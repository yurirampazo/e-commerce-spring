package br.com.dominio.projetoecommerce.model;

import br.com.dominio.projetoecommerce.util.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import javax.persistence.Entity;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class PagamentoComBoleto extends Pagamento {

  @JsonFormat(pattern = "dd-MM-yyyy | HH:mm")
  private LocalDateTime dataPagamento;

  @JsonFormat(pattern = "dd-MM-yyyy | HH:mm")
  private LocalDateTime dataVencimento;

  public PagamentoComBoleto(Integer id, EstadoPagamento estadoPagamento, Pedido pedido,
                            LocalDateTime dataPagamento, LocalDateTime dataVencimento) {
    super(id, estadoPagamento, pedido);
    this.dataPagamento = dataPagamento;
    this.dataVencimento = dataVencimento;
  }
}