package br.com.dominio.projetoecommerce.controller;

import br.com.dominio.projetoecommerce.domain.Produto;
import br.com.dominio.projetoecommerce.domain.dto.ProdutoDto;
import br.com.dominio.projetoecommerce.service.ProdutoService;
import br.com.dominio.projetoecommerce.util.URL;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

  private final ProdutoService produtoService;

  @GetMapping("/page")
  public ResponseEntity<Page<ProdutoDto>> findPage(@RequestParam(name = "page", defaultValue = "0")
                                                     Integer page,
                                                   @RequestParam(name = "linesPerPage", defaultValue = "24")
                                                   Integer linesPerPage,
                                                   @RequestParam(name = "orderBy", defaultValue = "nome")
                                                     String orderBy,
                                                   @RequestParam(name = "direction", defaultValue = "ASC")
                                                     String direction) {

    return ResponseEntity.ok().body(produtoService.findPage(page, linesPerPage, direction, orderBy));
  }

  @GetMapping("/search")
  public ResponseEntity<Page<ProdutoDto>> search(@RequestParam(name = "nome", defaultValue = "") String nome,
                                                 @RequestParam(name = "categorias", defaultValue = "") String categorias,
                                                 @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                 @RequestParam(name = "linesPerPage", defaultValue = "24")
                                                   Integer linesPerPage,
                                                 @RequestParam(name = "orderBy", defaultValue = "nome") String orderBy,
                                                 @RequestParam(name = "direction", defaultValue = "ASC") String direction) {

    List<Integer> list = URL.decodeIntList(categorias);
    String nomeDecoded = URL.decodeParam(nome);
    return ResponseEntity.ok().body(produtoService.search(nomeDecoded, list, page, linesPerPage, direction, orderBy));
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
