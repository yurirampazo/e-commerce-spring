package br.com.dominio.projetoecommerce.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PedidoDto {
  private Integer id;
  private LocalDateTime instante;
  private ClienteDto cliente;
  private EnderecoDto endereco;
  private BigDecimal valorTotal;
  private PagamentoDto pagamento;
}
