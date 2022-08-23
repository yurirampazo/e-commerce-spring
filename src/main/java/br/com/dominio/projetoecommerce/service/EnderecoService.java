package br.com.dominio.projetoecommerce.service;

import br.com.dominio.projetoecommerce.exception.IdNotFoundException;
import br.com.dominio.projetoecommerce.model.Endereco;
import br.com.dominio.projetoecommerce.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

  @Autowired
  private EnderecoRepository enderecoRepository;

  public List<Endereco> findAll() {
    return enderecoRepository.findAll();
  }

  public Endereco findById(Integer id) {
    return enderecoRepository.findById(id).orElseThrow(() ->
          new IdNotFoundException(id));
  }

  public Endereco postEndereco(Endereco endereco) {
    return enderecoRepository.save(endereco);
  }
}
