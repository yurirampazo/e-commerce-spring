package br.com.dominio.projetoecommerce.service;

import br.com.dominio.projetoecommerce.exception.DataIntegrityException;
import br.com.dominio.projetoecommerce.exception.DocumentNotFoundException;
import br.com.dominio.projetoecommerce.exception.DocumentNumberAlreadyExistsException;
import br.com.dominio.projetoecommerce.exception.EmailNotFoundException;
import br.com.dominio.projetoecommerce.exception.IdNotFoundException;
import br.com.dominio.projetoecommerce.exception.MapToDtoException;
import br.com.dominio.projetoecommerce.exception.MapToModelException;
import br.com.dominio.projetoecommerce.exception.PageNotFoundException;
import br.com.dominio.projetoecommerce.model.Cliente;
import br.com.dominio.projetoecommerce.model.Endereco;
import br.com.dominio.projetoecommerce.model.dto.ClienteDto;
import br.com.dominio.projetoecommerce.model.dto.NewClienteDto;
import br.com.dominio.projetoecommerce.repository.ClienteRepository;
import br.com.dominio.projetoecommerce.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

  public List<NewClienteDto> findAll() {
    return clienteRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
  }

  public Page<NewClienteDto> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
    PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction.toUpperCase()), orderBy);
    Page<Cliente> list = clienteRepository.findAll(pageRequest);
    try {
      return list.map(this::toDto);
    } catch (EmptyStackException | IndexOutOfBoundsException e) {
      throw new PageNotFoundException(page);
    }
  }

  public NewClienteDto findClienteById(Integer id) {
    return toDto(clienteRepository.findById(id).orElseThrow(() ->
          new IdNotFoundException(id)));
  }

  public NewClienteDto findByCpfCnpj(String cpfCnpj) {
      return toDto(clienteRepository.findClienteByCpfCnpjContainingIgnoreCase(cpfCnpj));
  }

  public NewClienteDto findByEmail(String email) {
    return toDto(clienteRepository.findClienteByEmailContainingIgnoreCase(email));
  }

  @Transactional
  public NewClienteDto postCliente(NewClienteDto cliente) {
    Cliente c1 = clienteRepository.save(toModel(cliente));
    for (Endereco x : cliente.getEnderecos()) {
      x.setCliente(c1);
    }
    enderecoRepository.saveAll(c1.getEnderecos());
    return findByCpfCnpj(c1.getCpfCnpj());
  }


  public void putCliente(Integer id, ClienteDto clienteAlterado) {
    Cliente cliente = clienteRepository.findById(id)
          .orElseThrow(IdNotFoundException::new);

    cliente.setNome(clienteAlterado.getNome());
    cliente.setEmail(clienteAlterado.getEmail());
    cliente.addTelefone(clienteAlterado.getTelefone());
    cliente.addEndereco(clienteAlterado.getEndereco());
    clienteRepository.save(cliente);
  }

  public void deleteCliente(Integer id) {
    findClienteById(id);
    try {
      clienteRepository.deleteById(id);
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException();
    }
  }

  public NewClienteDto toDto(Cliente model) {
    if (model == null) {
      throw new MapToDtoException();
    }
    NewClienteDto dto = new NewClienteDto();
    dto.setNome(model.getNome());
    dto.setCpfCnpj(model.getCpfCnpj());
    dto.setEmail(model.getEmail());
    dto.setTipo(model.getTipo());
    dto.setTelefones(model.getTelefones());
    model.getEnderecos().forEach(dto::addEndereco);
    return dto;
  }

  public Cliente toModel(NewClienteDto dto) {
    if (dto == null) {
      throw new MapToModelException();
    }

    Cliente model = new Cliente();
    model.setNome(dto.getNome());
    model.setCpfCnpj(dto.getCpfCnpj());
    model.setEmail(dto.getEmail());
    model.setTipo(dto.getTipo());
    dto.getEnderecos().forEach(model::addEndereco);
    model.addTelefones(dto.getTelefones());
    return model;
  }
}
