package br.com.dominio.projetoecommerce.controller;

import br.com.dominio.projetoecommerce.domain.dto.ClienteDto;
import br.com.dominio.projetoecommerce.domain.dto.NewClienteDto;
import br.com.dominio.projetoecommerce.mapper.ClienteMapper;
import br.com.dominio.projetoecommerce.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

  private final ClienteService clienteService;

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
    return ResponseEntity.ok(ClienteMapper.INSTANCE.toDto(clienteService.findByCpfCnpj(cpfCnpj)));
  }

  @GetMapping("/email/{email}")
  public ResponseEntity<ClienteDto> findClienteByEmail(@PathVariable String email) {
    return ResponseEntity.ok(clienteService.findByEmail(email));
  }

  @PostMapping
  public ResponseEntity<Void> postCliente(@Valid @RequestBody NewClienteDto dto) {
    clienteService.postCliente(dto);
    URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/clientes")
          .toUriString());
    return ResponseEntity.created(uri).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> putCliente(@Valid @PathVariable Integer id, @RequestBody ClienteDto cliente) {
    clienteService.putCliente(id, cliente);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCliente(@PathVariable Integer id) {
    clienteService.deleteCliente(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
