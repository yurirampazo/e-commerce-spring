package br.com.dominio.projetoecommerce.service;

import br.com.dominio.projetoecommerce.exception.IdNotFoundException;
import br.com.dominio.projetoecommerce.exception.PostNotAllowedException;
import br.com.dominio.projetoecommerce.model.Cidade;
import br.com.dominio.projetoecommerce.model.Estado;
import br.com.dominio.projetoecommerce.repository.CidadeRepository;
import br.com.dominio.projetoecommerce.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

  @Autowired
  private EstadoRepository estadoRepository;

  public List<Estado> findAll() {
    return estadoRepository.findAll();
  }

  public Estado findById(Integer id) {
    return estadoRepository.findById(id).orElseThrow(() ->
          new IdNotFoundException(id));
  }

  public Estado postEstado(Estado estado) {
    boolean exists = estadoRepository.findEstadoByNomeContainingIgnoreCase(estado.getNome()).isPresent();

    if(!exists) {
      return estadoRepository.save(estado);
    } else {
      throw new PostNotAllowedException("Objeto com mesmo nome já exite!");
    }
  }
}
