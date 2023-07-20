package br.com.dominio.projetoecommerce.service;

import br.com.dominio.projetoecommerce.domain.Cliente;
import br.com.dominio.projetoecommerce.domain.Endereco;
import br.com.dominio.projetoecommerce.domain.dto.ClienteDto;
import br.com.dominio.projetoecommerce.domain.dto.NewClienteDto;
import br.com.dominio.projetoecommerce.exception.DataIntegrityException;
import br.com.dominio.projetoecommerce.exception.IdNotFoundException;
import br.com.dominio.projetoecommerce.exception.PageNotFoundException;
import br.com.dominio.projetoecommerce.mapper.ClienteMapper;
import br.com.dominio.projetoecommerce.mapper.EnderecoMapper;
import br.com.dominio.projetoecommerce.repository.ClienteRepository;
import br.com.dominio.projetoecommerce.repository.EnderecoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClienteService {

  private final PasswordEncoder encoder;
  private final ClienteRepository clienteRepository;
  private final EnderecoRepository enderecoRepository;

  public Page<ClienteDto> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
    PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction.toUpperCase()), orderBy);
    Page<Cliente> list = clienteRepository.findAll(pageRequest);
    try {
      return list.map(ClienteMapper.INSTANCE::toDto);
    } catch (EmptyStackException | IndexOutOfBoundsException e) {
      throw new PageNotFoundException(page);
    }
  }

  public ClienteDto findClienteById(Integer id) {
    return ClienteMapper.INSTANCE.toDto(clienteRepository.findById(id).orElseThrow(() ->
          new IdNotFoundException(id)));
  }

  public Cliente findByCpfCnpj(String cpfCnpj) {
      return clienteRepository.findClienteByCpfCnpjContainingIgnoreCase(cpfCnpj);
  }

  public Optional<Cliente> findById(Integer id) {
    return clienteRepository.findById(id);
  }

  public ClienteDto findByEmail(String email) {
    return ClienteMapper.INSTANCE.toDto(clienteRepository.findByEmail(email));
  }

  @Transactional
  public void postCliente(NewClienteDto cliente) {
    log.info("POST User Request: {}", cliente.getNome());
    cliente.setSenha(encoder.encode(cliente.getSenha()));
    Cliente c1 = clienteRepository.save(ClienteMapper.INSTANCE.fromNewClientToDto(cliente));
    List<Endereco> enderecos = cliente.getEnderecos().stream().map(EnderecoMapper.INSTANCE::toModel).toList();
    for (Endereco x : enderecos) {
      x.setCliente(c1);
    }
    enderecoRepository.saveAll(c1.getEnderecos());
    log.info("Succefully Included user in database!");
  }


  public void putCliente(Integer id, ClienteDto clienteAlterado) {
    log.info("Starting update user: {}", id);
    Cliente cliente = clienteRepository.findById(id)
          .orElseThrow(IdNotFoundException::new);

    cliente.setNome(clienteAlterado.getNome());
    cliente.setEmail(clienteAlterado.getEmail());
    clienteRepository.save(cliente);
    log.info("Succefully updated user: {}", id);
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
