package br.com.dominio.projetoecommerce.controller;

import br.com.dominio.projetoecommerce.model.Endereco;
import br.com.dominio.projetoecommerce.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

  @Autowired
  private EnderecoService enderecoService;

  @GetMapping
  public ResponseEntity<List<Endereco>> findAll() {
    return ResponseEntity.ok(enderecoService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Endereco> findById(@PathVariable Integer id) {
    return ResponseEntity.ok(enderecoService.findById(id));
  }

  @PostMapping
  public ResponseEntity<Endereco> postEndereco(@RequestBody Endereco endereco) {
    enderecoService.postEndereco(endereco);
    return ResponseEntity.ok(enderecoService.postEndereco(endereco));
  }
}
