package br.com.dominio.projetoecommerce.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
@JsonInclude
public class PedidoDto implements Serializable {

  private Integer id;
  private String clienteNome;
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDateTime instante;
  private PagamentoDto pagamento;
  private EnderecoDto enderecoDeEntrega;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getClienteNome() {
    return clienteNome;
  }

  public void setClienteNome(String clienteNome) {
    this.clienteNome = clienteNome;
  }

  public LocalDateTime getInstante() {
    return instante;
  }

  public PagamentoDto getPagamento() {
    return pagamento;
  }

  public void setPagamento(PagamentoDto pagamento) {
    this.pagamento = pagamento;
  }

  public void setInstante(LocalDateTime instante) {
    this.instante = instante;
  }

  public EnderecoDto getEnderecoDeEntrega() {
    return enderecoDeEntrega;
  }

  public void setEnderecoDeEntrega(EnderecoDto enderecoDeEntrega) {
    this.enderecoDeEntrega = enderecoDeEntrega;
  }
}
