package br.com.dominio.projetoecommerce.controller;

import br.com.dominio.projetoecommerce.domain.Pagamento;
import br.com.dominio.projetoecommerce.service.PagamentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {
  private final PagamentoService pagamentoService;

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
