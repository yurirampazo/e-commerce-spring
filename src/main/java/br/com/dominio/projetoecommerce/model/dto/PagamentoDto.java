package br.com.dominio.projetoecommerce.model.dto;


import br.com.dominio.projetoecommerce.exception.MapToModelException;
import br.com.dominio.projetoecommerce.model.Pagamento;
import br.com.dominio.projetoecommerce.model.PagamentoComBoleto;
import br.com.dominio.projetoecommerce.model.PagamentoComCartao;
import br.com.dominio.projetoecommerce.model.Pedido;
import br.com.dominio.projetoecommerce.util.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NoArgsConstructor;
import org.springframework.dao.TypeMismatchDataAccessException;

import java.time.LocalDateTime;

@NoArgsConstructor
@JsonInclude
public class PagamentoDto {

  private Integer id;
  private Integer estadoPagamento;
  private Pedido pedido;
  @JsonFormat(pattern = "dd-MM-yyyy, HH:mm")
  private LocalDateTime dataPagamento;
  @JsonFormat(pattern = "dd-MM-yyyy, HH:mm")
  private LocalDateTime dataVencimento;
  private Integer numeroDeparcelas;

  public PagamentoDto(Integer id, EstadoPagamento estadoPagamento, Pedido pedido) {
    this.id = id;
    this.estadoPagamento = estadoPagamento.getEstado();
    this.pedido = pedido;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public EstadoPagamento getEstadoPagamento() {
    return EstadoPagamento.toEnum(estadoPagamento);
  }

  public void setEstadoPagamento(EstadoPagamento estadoPagamento) {
    this.estadoPagamento = estadoPagamento.getEstado();
  }

  public Pedido getPedido() {
    return pedido;
  }

  public void setPedido(Pedido pedido) {
    this.pedido = pedido;
  }

  public void setEstadoPagamento(Integer estadoPagamento) {
    this.estadoPagamento = estadoPagamento;
  }

  public LocalDateTime getDataPagamento() {
    return dataPagamento;
  }

  public void setDataPagamento(LocalDateTime dataPagamento) {
    this.dataPagamento = dataPagamento;
  }

  public LocalDateTime getDataVencimento() {
    return dataVencimento;
  }

  public void setDataVencimento(LocalDateTime dataVencimento) {
    this.dataVencimento = dataVencimento;
  }

  public Integer getNumeroDeparcelas() {
    return numeroDeparcelas;
  }

  public void setNumeroDeparcelas(Integer numeroDeparcelas) {
    this.numeroDeparcelas = numeroDeparcelas;
  }

  public static Pagamento toModel(PagamentoDto dto) {
    if (dto == null) {
      throw new MapToModelException();
    }

    if (dto.getDataPagamento() != null || dto.getDataVencimento() != null) {
      PagamentoComBoleto model = new PagamentoComBoleto();
      model.setId(dto.getId());
      model.setPedido(dto.getPedido());
      model.setEstadoPagamento(dto.getEstadoPagamento());
      model.setDataVencimento(dto.getDataVencimento());
      model.setDataPagamento(dto.getDataPagamento());
      return model;
    } else if (dto.getNumeroDeparcelas() != null) {
      PagamentoComCartao model = new PagamentoComCartao();
      model.setId(dto.getId());
      model.setPedido(dto.getPedido());
      model.setEstadoPagamento(dto.getEstadoPagamento());
      model.setNumeroDeparcelas(dto.getNumeroDeparcelas());
      return model;
    }
    throw new TypeMismatchDataAccessException("Não foi possível reconhcer o tipo de pagamento, " +
          "verifique se foram preenchidos dados ambíguos quanto ao tipo de pagamento." +
          "Exemplo: Número de parcelas para pagamanto com boleto!");
  }
}
