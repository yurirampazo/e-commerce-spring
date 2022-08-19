package br.com.dominio.projetoecommerce.controller;


import br.com.dominio.projetoecommerce.exception.IdNotFoundException;
import br.com.dominio.projetoecommerce.model.Categoria;
import br.com.dominio.projetoecommerce.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

  @Autowired
  private CategoriaService categoriaService;

  @GetMapping
  public ResponseEntity<List<Categoria>> listar() {
    return ResponseEntity.ok().body(categoriaService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Categoria> findById(@PathVariable Integer id) {
    return ResponseEntity.ok().body(categoriaService.findById(id).orElseThrow(
          () -> new IdNotFoundException("Id não encontrado")));
  }

  @PostMapping
  public ResponseEntity<Categoria> postCategoria(@RequestBody Categoria categoria) {
    return ResponseEntity.status(201).body(categoria);
  }

}
