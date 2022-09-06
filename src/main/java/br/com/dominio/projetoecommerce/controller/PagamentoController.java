package br.com.dominio.projetoecommerce.controller;

import br.com.dominio.projetoecommerce.model.Pagamento;
import br.com.dominio.projetoecommerce.service.PagamentoService;
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
@RequestMapping("/pagamentos")
public class PagamentoController {

  @Autowired
  private PagamentoService pagamentoService;

  @GetMapping
  public ResponseEntity<List<? extends Pagamento>> findAll() {
    return ResponseEntity.ok(pagamentoService.findAllPagamentos());
  }

  @GetMapping("/{id}")
  public ResponseEntity<? extends Pagamento> findPedidoById(@PathVariable Integer id) {
    return ResponseEntity.ok().body(pagamentoService.findPagamentoById(id));
  }

  @PostMapping
  public ResponseEntity<Pagamento> postPagamento(@Valid @RequestBody Pagamento pagamento) {
    pagamentoService.postPagamaneto(pagamento);
    return ResponseEntity.status(HttpStatus.CREATED).body(pagamento);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> putPagamento
        (@Valid @RequestBody Pagamento pagamento, @PathVariable Integer id) {

    pagamentoService.putPagamento(pagamento, id);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletePagamento(@PathVariable Integer id) {
    pagamentoService.deletePagamento(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
