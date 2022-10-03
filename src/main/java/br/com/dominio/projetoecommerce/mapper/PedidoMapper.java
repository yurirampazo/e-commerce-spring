package br.com.dominio.projetoecommerce.mapper;

import br.com.dominio.projetoecommerce.exception.MapToDtoException;
import br.com.dominio.projetoecommerce.exception.MapToModelException;
import br.com.dominio.projetoecommerce.model.Pedido;
import br.com.dominio.projetoecommerce.model.dto.PedidoDto;

public class PedidoMapper {
  public static Pedido toModel(PedidoDto dto) {
    if (dto == null) {
      throw new MapToModelException();
    }

    Pedido model = new Pedido();
    model.setId(dto.getId());
    model.setEnderecoDeEntrega(EnderecoMapper.toModel(dto.getEnderecoDeEntrega()));
    model.setPagamento(PagamentoMapper.toModel(dto.getPagamento()));
    return model;
  }

  public static PedidoDto toDto(Pedido model) {
    if (model == null) {
      throw new MapToDtoException();
    }
    PedidoDto dto = new PedidoDto();
    dto.setId(model.getId());
    dto.setPagamento(PagamentoMapper.toDto(model.getPagamento()));
    dto.setEnderecoDeEntrega(EnderecoMapper.toDto(model.getEnderecoDeEntrega()));
    dto.setClienteNome(model.getClienteNome());
    dto.setInstante(model.getInstante());
    dto.setItens(model.getItens());
    dto.setValorTotal(model.getValorTotal());
    return dto;
  }
}
