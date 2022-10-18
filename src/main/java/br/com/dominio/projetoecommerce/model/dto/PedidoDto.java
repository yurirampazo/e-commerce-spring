package br.com.dominio.projetoecommerce.model.dto;

import br.com.dominio.projetoecommerce.model.ItemPedido;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Getter
@NoArgsConstructor
@JsonInclude
public class PedidoDto implements Serializable {

  private Integer id;
//  private String clienteNome;
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDateTime instante;
  private PagamentoDto pagamento;
  private EnderecoDto enderecoDeEntrega;
  private Set<ItemPedido> itens = new HashSet<>();
  private BigDecimal valorTotal;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

//  public String getClienteNome() {
//    return clienteNome;
//  }

//  public void setClienteNome(String clienteNome) {
//    this.clienteNome = clienteNome;
//  }

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

  public Set<ItemPedido> getItens() {
    return itens;
  }

  public void setItens(Set<ItemPedido> itens) {
    this.itens = itens;
  }

  public BigDecimal getValorTotal() {
    return valorTotal;
  }

  public void setValorTotal(BigDecimal valorTotal) {
    this.valorTotal = valorTotal;
  }
}
