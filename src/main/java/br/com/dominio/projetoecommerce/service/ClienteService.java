package br.com.dominio.projetoecommerce.service;

import br.com.dominio.projetoecommerce.exception.DocumentNumberAlreadyExistsException;
import br.com.dominio.projetoecommerce.exception.IdNotFoundException;
import br.com.dominio.projetoecommerce.model.Cliente;
import br.com.dominio.projetoecommerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

  @Autowired
  private ClienteRepository clienteRepository;

  public List<Cliente> findAll() {
    return clienteRepository.findAll();
  }

  public Cliente findClienteById(Integer id) {
    return clienteRepository.findById(id).orElseThrow(() ->
          new IdNotFoundException(id));
  }

  public Cliente findByCpfCnpj(String cpfCnpj) {
    return clienteRepository.findClienteByCpfCnpjContainingIgnoreCase(cpfCnpj).orElseThrow(() ->
          new IdNotFoundException("CPF:" + cpfCnpj + " não encontrado"));
  }

  public Cliente findByEmail(String email) {
    return clienteRepository.findClienteByEmailContainingIgnoreCase(email).orElseThrow(() ->
          new IdNotFoundException("E-mail:" + email + " não encontrado"));
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
    Cliente cliente = findClienteById(id);

    cliente.setNome(clienteAlterado.getNome());
    cliente.setCpfCnpj(clienteAlterado.getCpfCnpj());
    cliente.setEmail(clienteAlterado.getEmail());
    cliente.setTipo(clienteAlterado.getTipo());

    clienteRepository.save(clienteAlterado);
  }

  public void deleteCliente(Integer id) {
    findClienteById(id);
    clienteRepository.deleteById(id);
  }
}
