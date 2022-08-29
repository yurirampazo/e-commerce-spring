package br.com.dominio.projetoecommerce.service;

import br.com.dominio.projetoecommerce.exception.IdNotFoundException;
import br.com.dominio.projetoecommerce.exception.PostNotAllowedException;
import br.com.dominio.projetoecommerce.model.Cidade;
import br.com.dominio.projetoecommerce.model.Pedido;
import br.com.dominio.projetoecommerce.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

  @Autowired
  private PedidoRepository pedidoRepository;

  public List<Pedido> findAllPedidos() {
    return pedidoRepository.findAll();
  }

  public Pedido findPedidoById(Integer id) {
    return pedidoRepository.findById(id).orElseThrow(() ->
          new IdNotFoundException("Id: " + id + "do pedido não encontrado!"));
  }

  public Pedido postPedido(Pedido pedido) {
    boolean exists = pedidoRepository.findById(pedido.getId()).isPresent();

    if(!exists) {
      return pedidoRepository.save(pedido);
    } else {
      throw new PostNotAllowedException("Pedido duplicado!");
    }
  }

  public void putPedido(Integer id, Pedido pedidoAlterado) {
    Pedido pedido = findPedidoById(id);
    pedido.setCliente(pedidoAlterado.getCliente());
    pedido.setPagamento(pedidoAlterado.getPagamento());
    pedido.setEnderecoDeEntrega(pedidoAlterado.getEnderecoDeEntrega());

    pedidoRepository.save(pedido);
  }

  public void deletePedido(Integer id) {
    findPedidoById(id);
    pedidoRepository.deleteById(id);
  }


}
