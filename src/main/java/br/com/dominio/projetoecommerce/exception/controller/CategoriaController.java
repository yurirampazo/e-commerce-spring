package br.com.dominio.projetoecommerce.exception.controller;


import br.com.dominio.projetoecommerce.model.Categoria;
import br.com.dominio.projetoecommerce.model.dto.CategoriaDto;
import br.com.dominio.projetoecommerce.service.CategoriaService;
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
@RequestMapping("/categorias")
public class CategoriaController {

  @Autowired
  private CategoriaService categoriaService;

  @GetMapping("/page")
  public ResponseEntity<Page<CategoriaDto>> findPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                     @RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                     @RequestParam(name = "orderBy", defaultValue = "nome") String orderBy,
                                                     @RequestParam(name = "direction", defaultValue = "ASC") String direction) {

    return ResponseEntity.ok().body(categoriaService.findPage(page, linesPerPage, direction, orderBy));
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoriaDto> findById(@PathVariable Integer id) {
    return ResponseEntity.ok().body(categoriaService.findCategoriaById(id));
  }

  @PostMapping
  public ResponseEntity<Categoria> postCategoria(@Valid @RequestBody Categoria categoria) {
    categoriaService.postCategoria(categoria);
    return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> updateCategoria(@Valid @RequestBody Categoria categoriaAlterada, @PathVariable Integer id) {
    categoriaService.putCategoria(categoriaAlterada, id);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deletarCategoria(@PathVariable Integer id) {
    categoriaService.deleteCategoriaById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
