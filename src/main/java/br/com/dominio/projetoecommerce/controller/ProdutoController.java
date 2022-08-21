package br.com.dominio.projetoecommerce.controller;

import br.com.dominio.projetoecommerce.exception.IdNotFoundException;
import br.com.dominio.projetoecommerce.model.Produto;
import br.com.dominio.projetoecommerce.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

  @Autowired
  private ProdutoService produtoService;

  @GetMapping
  public ResponseEntity<List<Produto>> findAll() {
    return ResponseEntity.ok().body(produtoService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Produto> findById(@PathVariable Integer id) {
    return ResponseEntity.ok(produtoService.findById(id));
  }

  @PostMapping
  public ResponseEntity<Produto> postProduto(@RequestBody Produto produto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.postProduto(produto));
  }

}
