package br.com.dominio.projetoecommerce.exception.controller;

import br.com.dominio.projetoecommerce.model.Produto;
import br.com.dominio.projetoecommerce.model.dto.ProdutoDto;
import br.com.dominio.projetoecommerce.service.ProdutoService;
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
@RequestMapping("/produtos")
public class ProdutoController {

  @Autowired
  private ProdutoService produtoService;

  @GetMapping("/page")
  public ResponseEntity<Page<ProdutoDto>> findPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                   @RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                   @RequestParam(name = "orderBy", defaultValue = "nome") String orderBy,
                                                   @RequestParam(name = "direction", defaultValue = "ASC") String direction) {

    return ResponseEntity.ok().body(produtoService.findPage(page, linesPerPage, direction, orderBy));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProdutoDto> findById(@PathVariable Integer id) {
    return ResponseEntity.ok(produtoService.findProdutoById(id));
  }

  @PostMapping
  public ResponseEntity<Produto> postProduto(@Valid @RequestBody Produto produto) {
    produtoService.postProduto(produto);
    return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.postProduto(produto));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> putProduto(@Valid @RequestBody Produto produto,
                                         @PathVariable Integer id) {
    produtoService.putProduto(produto, id);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduto(@PathVariable Integer id) {
    produtoService.deleteProduto(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
