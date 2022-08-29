package br.com.dominio.projetoecommerce.controller;

import br.com.dominio.projetoecommerce.model.Estado;
import br.com.dominio.projetoecommerce.service.EstadoService;
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
@RequestMapping("/estados")
public class EstadoController {

  @Autowired
  private EstadoService estadoService;

  @GetMapping
  public ResponseEntity<List<Estado>> findAll() {
    return ResponseEntity.ok(estadoService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Estado> findById(@PathVariable Integer id) {
    return ResponseEntity.ok(estadoService.findById(id));
  }

  @PostMapping
  public ResponseEntity<Estado> postEstado(@RequestBody Estado estado) {
    estadoService.postEstado(estado);
    return ResponseEntity.status(HttpStatus.CREATED).body(estado);
  }

}
