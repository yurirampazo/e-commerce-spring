package br.com.dominio.projetoecommerce.domain.dto;


import br.com.dominio.projetoecommerce.domain.enums.EstadoPagamento;
import br.com.dominio.projetoecommerce.domain.Pedido;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NoArgsConstructor;

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
}
