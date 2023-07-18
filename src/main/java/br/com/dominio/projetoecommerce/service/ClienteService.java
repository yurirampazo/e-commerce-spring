package br.com.dominio.projetoecommerce.service;

import br.com.dominio.projetoecommerce.domain.Cliente;
import br.com.dominio.projetoecommerce.domain.Endereco;
import br.com.dominio.projetoecommerce.domain.dto.ClienteDto;
import br.com.dominio.projetoecommerce.domain.dto.NewClienteDto;
import br.com.dominio.projetoecommerce.exception.DataIntegrityException;
import br.com.dominio.projetoecommerce.exception.IdNotFoundException;
import br.com.dominio.projetoecommerce.exception.PageNotFoundException;
import br.com.dominio.projetoecommerce.mapper.ClienteMapper;
import br.com.dominio.projetoecommerce.repository.ClienteRepository;
import br.com.dominio.projetoecommerce.repository.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.EmptyStackException;

@Service
public class ClienteService {

  @Autowired
  private PasswordEncoder encoder;

  @Autowired
  private ClienteRepository clienteRepository;

  @Autowired
  private EnderecoRepository enderecoRepository;

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
    return ClienteMapper.INSTANCE.newClienteDto(clienteRepository.findByEmail(email));
  }

  @Transactional
  public NewClienteDto postCliente(NewClienteDto cliente) {
    cliente.setSenha(encoder.encode(cliente.getSenha()));
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
