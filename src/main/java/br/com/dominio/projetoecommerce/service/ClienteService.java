package br.com.dominio.projetoecommerce.service;

import br.com.dominio.projetoecommerce.exception.DataIntegrityException;
import br.com.dominio.projetoecommerce.exception.DocumentNumberAlreadyExistsException;
import br.com.dominio.projetoecommerce.exception.IdNotFoundException;
import br.com.dominio.projetoecommerce.model.Cliente;
import br.com.dominio.projetoecommerce.model.dto.ClienteDto;
import br.com.dominio.projetoecommerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

  @Autowired
  private ClienteRepository clienteRepository;

  public List<ClienteDto> findAll() {
    return clienteRepository.findAll().stream().map(Cliente::toDto).collect(Collectors.toList());
  }

  public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
    PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
    return clienteRepository.findAll(pageRequest);
  }

  public ClienteDto findClienteById(Integer id) {
    return Cliente.toDto(clienteRepository.findById(id).orElseThrow(() ->
          new IdNotFoundException(id)));
  }

  public ClienteDto findByCpfCnpj(String cpfCnpj) {
    return Cliente.toDto(clienteRepository.findClienteByCpfCnpjContainingIgnoreCase(cpfCnpj).orElseThrow(() ->
          new IdNotFoundException("CPF:" + cpfCnpj + " não encontrado")));
  }

  public ClienteDto findByEmail(String email) {
    return Cliente.toDto(clienteRepository.findClienteByEmailContainingIgnoreCase(email).orElseThrow(() ->
          new IdNotFoundException("E-mail:" + email + " não encontrado")));
  }

  public Cliente postCliente(Cliente cliente) {
    boolean exists = clienteRepository.findClienteByCpfCnpjContainingIgnoreCase(cliente.getCpfCnpj()).isPresent();

    if (!exists) {
      return clienteRepository.save(cliente);
    } else {
      throw new DocumentNumberAlreadyExistsException();
    }
  }

  public void putCliente(Integer id, Cliente clienteAlterado) {
    Cliente cliente = ClienteDto.toModel(findClienteById(id));

    cliente.setNome(clienteAlterado.getNome());
    cliente.setCpfCnpj(clienteAlterado.getCpfCnpj());
    cliente.setEmail(clienteAlterado.getEmail());
    cliente.setTipo(clienteAlterado.getTipo());

    clienteRepository.save(clienteAlterado);
  }

  public void deleteCliente(Integer id) {
    findClienteById(id);
    try {
      clienteRepository.deleteById(id);
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException();
    }
  }
}
