package br.com.dominio.projetoecommerce.domain;

import br.com.dominio.projetoecommerce.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "pagamento_com_boleto")
@JsonTypeName("pagamentoComBoleto")
public class PagamentoComBoleto extends Pagamento {

  @JsonFormat(pattern = "dd-MM-yyyy, HH:mm")
  private LocalDateTime dataPagamento;

  @JsonFormat(pattern = "dd-MM-yyyy, HH:mm")
  private LocalDateTime dataVencimento;

  public PagamentoComBoleto(Integer id, EstadoPagamento estadoPagamento, Pedido pedido,
                            LocalDateTime dataPagamento, LocalDateTime dataVencimento) {
    super(id, estadoPagamento, pedido);
    this.dataPagamento = dataPagamento;
    this.dataVencimento = dataVencimento;
  }
}
