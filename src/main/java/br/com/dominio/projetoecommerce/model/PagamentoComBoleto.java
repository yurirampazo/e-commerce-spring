package br.com.dominio.projetoecommerce.model;

import br.com.dominio.projetoecommerce.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "pagamento_com_boleto")
public class PagamentoComBoleto extends Pagamento {

  @JsonFormat(pattern = "dd-MM-yyyy, HH:mm")
  @NotNull
  private LocalDateTime dataPagamento;

  @JsonFormat(pattern = "dd-MM-yyyy, HH:mm")
  @NotNull
  private LocalDateTime dataVencimento;

  public PagamentoComBoleto(Integer id, EstadoPagamento estadoPagamento, Pedido pedido,
                            LocalDateTime dataPagamento, LocalDateTime dataVencimento) {
    super(id, estadoPagamento, pedido);
    this.dataPagamento = dataPagamento;
    this.dataVencimento = dataVencimento;
  }
}
