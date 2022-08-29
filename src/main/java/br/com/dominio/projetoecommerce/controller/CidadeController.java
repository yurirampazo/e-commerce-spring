package br.com.dominio.projetoecommerce.controller;

import br.com.dominio.projetoecommerce.model.Cidade;
import br.com.dominio.projetoecommerce.service.CidadeService;
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
@RequestMapping("/cidades")
public class CidadeController {

  @Autowired
  private CidadeService cidadeService;

  @GetMapping
  public ResponseEntity<List<Cidade>> findAll() {
    return ResponseEntity.ok(cidadeService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Cidade> findById(@PathVariable Integer id) {
    return ResponseEntity.ok(cidadeService.findById(id));
  }

  @PostMapping
  public ResponseEntity<Cidade> postCidade(@RequestBody Cidade cidade) {
    cidadeService.postCidade(cidade);
    return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
  }

}
