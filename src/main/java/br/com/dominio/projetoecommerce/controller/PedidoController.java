package br.com.dominio.projetoecommerce.controller;

import br.com.dominio.projetoecommerce.model.Pedido;
import br.com.dominio.projetoecommerce.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

  @Autowired
  private PedidoService pedidoService;

  @GetMapping
  public ResponseEntity<List<Pedido>> findAll() {
    return ResponseEntity.ok(pedidoService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Pedido> findPedidoById(@PathVariable Integer id) {
    return ResponseEntity.ok().body(pedidoService.findById(id));
  }

  public ResponseEntity<Pedido> postPedido(@RequestBody Pedido pedido) {
    pedidoService.postPedido(pedido);
    return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
  }

}
