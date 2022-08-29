package br.com.dominio.projetoecommerce.controller;

import br.com.dominio.projetoecommerce.model.Cliente;
import br.com.dominio.projetoecommerce.repository.ClienteRepository;
import br.com.dominio.projetoecommerce.service.ClienteService;
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
    return ResponseEntity.ok(clienteService.findClienteById(id));
  }

  @GetMapping("/{cpfCnpj}")
  public ResponseEntity<Cliente> findClienteByCpfCnpj(@PathVariable String cpfCnpj) {
    return ResponseEntity.ok(clienteService.findByCpfCnpj(cpfCnpj));
  }

  @GetMapping("/{email}")
  public ResponseEntity<Cliente> findClienteByEmail(@PathVariable String email) {
    return ResponseEntity.ok(clienteService.findByEmail(email));
  }

  @PostMapping
  public ResponseEntity<Cliente> postCliente(@Valid @RequestBody Cliente cliente) {
    clienteService.postCliente(cliente);
    return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> putCliente(@Valid @PathVariable Integer id, @RequestBody Cliente cliente) {
    clienteService.putCliente(id, cliente);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) {
    clienteService.deleteCliente(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
