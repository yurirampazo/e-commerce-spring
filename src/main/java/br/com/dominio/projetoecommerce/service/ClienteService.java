package br.com.dominio.projetoecommerce.service;

import br.com.dominio.projetoecommerce.exception.DataIntegrityException;
import br.com.dominio.projetoecommerce.exception.IdNotFoundException;
import br.com.dominio.projetoecommerce.exception.PageNotFoundException;
import br.com.dominio.projetoecommerce.mapper.ClienteMapper;
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
    return clienteRepository.findAll().stream().map(ClienteMapper.INSTANCE::newClienteDto).collect(Collectors.toList());
  }

  public Page<NewClienteDto> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
    PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction.toUpperCase()), orderBy);
    Page<Cliente> list = clienteRepository.findAll(pageRequest);
    try {
      return list.map(ClienteMapper.INSTANCE::newClienteDto);
    } catch (EmptyStackException | IndexOutOfBoundsException e) {
      throw new PageNotFoundException(page);
    }
  }

  public NewClienteDto findClienteById(Integer id) {
    return ClienteMapper.INSTANCE.newClienteDto(clienteRepository.findById(id).orElseThrow(() ->
          new IdNotFoundException(id)));
  }

  public NewClienteDto findByCpfCnpj(String cpfCnpj) {
      return ClienteMapper.INSTANCE.newClienteDto(clienteRepository.findClienteByCpfCnpjContainingIgnoreCase(cpfCnpj));
  }

  public NewClienteDto findByEmail(String email) {
    return ClienteMapper.INSTANCE.newClienteDto(clienteRepository.findClienteByEmailContainingIgnoreCase(email));
  }

  @Transactional
  public NewClienteDto postCliente(NewClienteDto cliente) {
    Cliente c1 = clienteRepository.save(ClienteMapper.INSTANCE.fromNewClientToDto(cliente));
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
}
