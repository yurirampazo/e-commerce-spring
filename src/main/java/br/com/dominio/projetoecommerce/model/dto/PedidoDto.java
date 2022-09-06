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
  private final LocalDateTime instante = LocalDateTime.now();
  private Pagamento pagamento;
  private Cliente cliente;
  private Endereco enderecoDeEntrega;
  private Set<ItemPedido> itens = new HashSet<>();

  public void setId(Integer id) {
    this.id = id;
  }
  public void setClienteNome(String clienteNome) {
    this.clienteNome = clienteNome;
  }
  public void setPagamento(Pagamento pagamento) {
    this.pagamento = pagamento;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
    this.enderecoDeEntrega = enderecoDeEntrega;
  }

  public void setItens(Set<ItemPedido> itens) {
    this.itens = itens;
  }

  public static Pedido toModel(PedidoDto dto) {
    if (dto == null) {
      throw new MapToModelException();
    }

    Pedido model = new Pedido();
    model.setId(dto.getId());
    model.setEnderecoDeEntrega(dto.getEnderecoDeEntrega());
    model.setPagamento(dto.getPagamento());
    return model;
  }
}
