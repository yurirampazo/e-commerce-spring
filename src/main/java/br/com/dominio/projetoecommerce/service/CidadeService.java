package br.com.dominio.projetoecommerce.service;

import br.com.dominio.projetoecommerce.exception.IdNotFoundException;
import br.com.dominio.projetoecommerce.exception.PostNotAllowedException;
import br.com.dominio.projetoecommerce.model.Cidade;
import br.com.dominio.projetoecommerce.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

  @Autowired
  private CidadeRepository cidadeRepository;

  public List<Cidade> findAll() {
    return cidadeRepository.findAll();
  }

  public Cidade findById(Integer id) {
    return cidadeRepository.findById(id).orElseThrow(() ->
          new IdNotFoundException("Id não encontrado!"));
  }

  public Cidade postCidade(Cidade cidade) {
    boolean exists = cidadeRepository.findCidadeByNomeContainingIgnoreCase(cidade.getNome()).isPresent();

    if(!exists) {
      return cidadeRepository.save(cidade);
    } else {
      throw new PostNotAllowedException("Objeto com mesmo nome já exite!");
    }
  }
}
