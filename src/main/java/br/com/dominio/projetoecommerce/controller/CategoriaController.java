package br.com.dominio.projetoecommerce.controller;


import br.com.dominio.projetoecommerce.model.Categoria;
import br.com.dominio.projetoecommerce.model.Produto;
import br.com.dominio.projetoecommerce.service.CategoriaService;
import br.com.dominio.projetoecommerce.service.ProdutoService;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

  @Autowired
  private CategoriaService categoriaService;

  @GetMapping
  public ResponseEntity<List<Categoria>> listar() {
    return ResponseEntity.ok().body(categoriaService.findAllCategorias());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Categoria> findById(@PathVariable Integer id) {
    return ResponseEntity.ok().body(categoriaService.findCategoriaById(id));
  }

  @PostMapping
  public ResponseEntity<Categoria> postCategoria(@Valid @RequestBody Categoria categoria) {
    categoriaService.postCategoria(categoria);
    return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateCategoria(@Valid @RequestBody Categoria categoriaAlterada , @PathVariable Integer id) {
    categoriaService.putCategoria(categoriaAlterada, id);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deletarCategoria(@PathVariable Integer id) {
    categoriaService.deleteCategoriaById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
