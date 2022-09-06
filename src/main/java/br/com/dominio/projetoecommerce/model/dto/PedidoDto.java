package br.com.dominio.projetoecommerce.model.dto;

import br.com.dominio.projetoecommerce.exception.MapToModelException;
import br.com.dominio.projetoecommerce.model.Cliente;
import br.com.dominio.projetoecommerce.model.Endereco;
import br.com.dominio.projetoecommerce.model.ItemPedido;
import br.com.dominio.projetoecommerce.model.Pagamento;
import br.com.dominio.projetoecommerce.model.Pedido;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


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

  public static Pedido toModel(PedidoDto dto) {
    if (dto == null) {
      throw new MapToModelException();
    }

    Pedido model = new Pedido();
    model.setId(dto.getId());
    model.setEnderecoDeEntrega(EnderecoDto.toModel(dto.getEnderecoDeEntrega()));
    model.setPagamento(PagamentoDto.toModel(dto.getPagamento()));
    return model;
  }
}
