package br.com.dominio.projetoecommerce.service;

import br.com.dominio.projetoecommerce.exception.DataIntegrityException;
import br.com.dominio.projetoecommerce.exception.DocumentNumberAlreadyExistsException;
import br.com.dominio.projetoecommerce.exception.IdNotFoundException;
import br.com.dominio.projetoecommerce.exception.PageNotFoundException;
import br.com.dominio.projetoecommerce.model.Cliente;
import br.com.dominio.projetoecommerce.model.Endereco;
import br.com.dominio.projetoecommerce.model.dto.ClienteDto;
import br.com.dominio.projetoecommerce.repository.ClienteRepository;
import br.com.dominio.projetoecommerce.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import javax.transaction.Transactional;
import java.util.EmptyStackException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

  @Autowired
  private ClienteRepository clienteRepository;

  @Autowired
  private EnderecoRepository enderecoRepository;

  public List<ClienteDto> findAll() {
    return clienteRepository.findAll().stream().map(Cliente::toDto).collect(Collectors.toList());
  }

  public Page<ClienteDto> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
    PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction.toUpperCase()), orderBy);
    Page<Cliente> list = clienteRepository.findAll(pageRequest);
    try {
      return list.map(Cliente::toDto);
    } catch (EmptyStackException | IndexOutOfBoundsException e) {
      throw new PageNotFoundException(page);
    }
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

  @Transactional
  public void postCliente(ClienteDto cliente) {
    boolean exists = clienteRepository.findClienteByCpfCnpjContainingIgnoreCase(cliente.getCpfCnpj()).isPresent();

    if (!exists) {
      clienteRepository.save(ClienteDto.toModel(cliente));
      Cliente c1 = clienteRepository.findClienteByCpfCnpjContainingIgnoreCase(cliente.getCpfCnpj())
            .orElseThrow(IdNotFoundException::new);
      for (Endereco x : cliente.getEnderecos()) {
        x.setCliente(c1);
      }
      enderecoRepository.saveAll(cliente.getEnderecos());
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
