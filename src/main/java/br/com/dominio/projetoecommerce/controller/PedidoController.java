package br.com.dominio.projetoecommerce.controller;

import br.com.dominio.projetoecommerce.model.Pedido;
import br.com.dominio.projetoecommerce.model.dto.PedidoDto;
import br.com.dominio.projetoecommerce.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

  @Autowired
  private PedidoService pedidoService;

  @GetMapping
  public ResponseEntity<List<PedidoDto>> findAll() {
    return ResponseEntity.ok(pedidoService.findAllPedidos());
  }

  @GetMapping("/{id}")
  public ResponseEntity<PedidoDto> findPedidoById(@PathVariable Integer id) {
    return ResponseEntity.ok().body(pedidoService.findPedidoById(id));
  }

  @PostMapping
  public ResponseEntity<Pedido> postPedido(@Valid @RequestBody Pedido pedido) {
    pedidoService.postPedido(pedido);
    return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> putPedido(@Valid @PathVariable Integer id,
                                        @RequestBody Pedido pedido) {
    pedidoService.putPedido(id, pedido);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePedido(@PathVariable Integer id) {
    pedidoService.deletePedido(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
