package br.com.dominio.projetoecommerce.service;

import br.com.dominio.projetoecommerce.exception.DataIntegrityException;
import br.com.dominio.projetoecommerce.exception.IdNotFoundException;
import br.com.dominio.projetoecommerce.exception.PostNotAllowedException;
import br.com.dominio.projetoecommerce.model.Cidade;
import br.com.dominio.projetoecommerce.model.Pedido;
import br.com.dominio.projetoecommerce.model.dto.PedidoDto;
import br.com.dominio.projetoecommerce.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

  @Autowired
  private PedidoRepository pedidoRepository;

  public List<PedidoDto> findAllPedidos() {
    return pedidoRepository.findAll().stream().map(Pedido::toDto).collect(Collectors.toList());
  }

  public PedidoDto findPedidoById(Integer id) {
    return Pedido.toDto(pedidoRepository.findById(id).orElseThrow(() ->
          new IdNotFoundException("Id: " + id + "do pedido não encontrado!")));
  }

  public Pedido postPedido(Pedido pedido) {
    boolean exists = pedidoRepository.findById(pedido.getId()).isPresent();

    if (!exists) {
      return pedidoRepository.save(pedido);
    } else {
      throw new PostNotAllowedException("Pedido duplicado!");
    }
  }

  public void putPedido(Integer id, Pedido pedidoAlterado) {
    Pedido pedido = PedidoDto.toModel(findPedidoById(id));
    pedido.setCliente(pedidoAlterado.getCliente());
    pedido.setPagamento(pedidoAlterado.getPagamento());
    pedido.setEnderecoDeEntrega(pedidoAlterado.getEnderecoDeEntrega());

    pedidoRepository.save(pedido);
  }

  public void deletePedido(Integer id) {
    findPedidoById(id);
    try {
      pedidoRepository.deleteById(id);
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException();
    }
  }


}
