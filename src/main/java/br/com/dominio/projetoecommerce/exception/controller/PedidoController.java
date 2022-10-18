package br.com.dominio.projetoecommerce.exception.controller;

import br.com.dominio.projetoecommerce.mapper.PedidoMapper;
import br.com.dominio.projetoecommerce.model.Pedido;
import br.com.dominio.projetoecommerce.model.dto.PedidoDto;
import br.com.dominio.projetoecommerce.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

  @Autowired
  private PedidoService pedidoService;

  @GetMapping("/page")
  public ResponseEntity<Page<PedidoDto>> findPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                  @RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                  @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                  @RequestParam(name = "orderBy", defaultValue = "id") String orderBy) {
   return ResponseEntity.ok().body(pedidoService.findPage(page, linesPerPage, direction, orderBy));
  }

  @GetMapping("/{id}")
  public ResponseEntity<PedidoDto> findPedidoById(@PathVariable Integer id) {
    return ResponseEntity.ok().body(pedidoService.findPedidoById(id));
  }

  @PostMapping
  public ResponseEntity<PedidoDto> postPedido(@RequestBody Pedido pedido) {
    PedidoDto dto = pedidoService.postPedido(pedido);
    return ResponseEntity.status(HttpStatus.CREATED).body(dto);
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
