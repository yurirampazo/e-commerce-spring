package br.com.dominio.projetoecommerce.service;

import br.com.dominio.projetoecommerce.exception.DataIntegrityException;
import br.com.dominio.projetoecommerce.exception.IdNotFoundException;
import br.com.dominio.projetoecommerce.exception.PageNotFoundException;
import br.com.dominio.projetoecommerce.exception.PostNotAllowedException;
import br.com.dominio.projetoecommerce.model.Pedido;
import br.com.dominio.projetoecommerce.model.dto.PedidoDto;
import br.com.dominio.projetoecommerce.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.EmptyStackException;

@Service
public class PedidoService {

  @Autowired
  private PedidoRepository pedidoRepository;

  public Page<Pedido> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {

    PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction.toUpperCase()), orderBy);
    Page<Pedido> list = pedidoRepository.findAll(pageRequest);
    return list;
//    try {
//      return list.map(Pedido::toDto);
//    } catch (EmptyStackException | IndexOutOfBoundsException e) {
//      throw new PageNotFoundException(page);
//    }
  }

  public Pedido findPedidoById(Integer id) {
//    return Pedido.toDto(pedidoRepository.findById(id).orElseThrow(() ->
//          new IdNotFoundException("Id: " + id + "do pedido não encontrado!")));
    return pedidoRepository.findById(id).orElse(null);
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
    Pedido pedido = pedidoRepository.findById(id).orElse(null);
    assert pedido != null;
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
