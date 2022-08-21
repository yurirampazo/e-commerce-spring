package br.com.dominio.projetoecommerce.controller;

import br.com.dominio.projetoecommerce.model.Cliente;
import br.com.dominio.projetoecommerce.repository.ClienteRepository;
import br.com.dominio.projetoecommerce.service.ClienteService;
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
@RequestMapping("/clientes")
public class ClienteController {

  @Autowired
  private ClienteService clienteService;

  @GetMapping
  public ResponseEntity<List<Cliente>> findAll() {
    return ResponseEntity.ok(clienteService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Cliente> findClienteById(@PathVariable Integer id) {
    return ResponseEntity.ok(clienteService.findById(id));
  }

  @PostMapping
  public ResponseEntity<Cliente> postCliente(@RequestBody Cliente cliente) {
    return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
  }
}
