package br.com.dominio.projetoecommerce.controller;

import br.com.dominio.projetoecommerce.model.Pagamento;
import br.com.dominio.projetoecommerce.model.Pedido;
import br.com.dominio.projetoecommerce.service.PagamentoService;
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
@RequestMapping("/pagamentos")
public class PagamentoController {

  @Autowired
  private PagamentoService pagamentoService;

  @GetMapping
  public ResponseEntity<List<? extends Pagamento>> findAll() {
    return ResponseEntity.ok(pagamentoService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<? extends Pagamento> findPedidoById(@PathVariable Integer id) {
    return ResponseEntity.ok().body(pagamentoService.findById(id));
  }

  public ResponseEntity<? extends Pagamento> postPagamento(@RequestBody Pagamento pagamento) {
    pagamentoService.postPagamaneto(pagamento);
    return ResponseEntity.status(HttpStatus.CREATED).body(pagamento);
  }

}
