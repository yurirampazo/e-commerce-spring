package br.com.dominio.projetoecommerce.controller;

import br.com.dominio.projetoecommerce.model.Cliente;
import br.com.dominio.projetoecommerce.model.dto.ClienteDto;
import br.com.dominio.projetoecommerce.service.ClienteService;
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
@RequestMapping("/clientes")
public class ClienteController {

  @Autowired
  private ClienteService clienteService;

  @GetMapping("/page")
  public ResponseEntity<Page<ClienteDto>> findPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                   @RequestParam(name = "linesPerPage", defaultValue = "24") Integer linerPerPage,
                                                   @RequestParam(name = "direction", defaultValue = "ASC") String direction,
                                                   @RequestParam(name = "orderBy", defaultValue = "nome") String orderBy) {
    return ResponseEntity.ok(clienteService.findPage(page, linerPerPage, direction, orderBy));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ClienteDto> findClienteById(@PathVariable Integer id) {
    return ResponseEntity.ok(clienteService.findClienteById(id));
  }

  @GetMapping("/document/{cpfCnpj}")
  public ResponseEntity<ClienteDto> findClienteByCpfCnpj(@PathVariable String cpfCnpj) {
    return ResponseEntity.ok(clienteService.findByCpfCnpj(cpfCnpj));
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<ClienteDto> findClienteByEmail(@PathVariable String email) {
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
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
