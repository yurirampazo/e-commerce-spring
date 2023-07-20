package br.com.dominio.projetoecommerce.controller;

import br.com.dominio.projetoecommerce.domain.Pedido;
import br.com.dominio.projetoecommerce.domain.dto.PedidoDto;
import br.com.dominio.projetoecommerce.mapper.PedidoMapper;
import br.com.dominio.projetoecommerce.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
  public ResponseEntity<Void> postPedido(@RequestBody PedidoDto pedido) {
    pedidoService.postPedido(pedido);
    URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/pedidos").toUriString());
    return ResponseEntity.created(uri).build();
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
