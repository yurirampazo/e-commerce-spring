package br.com.dominio.projetoecommerce.service;

import br.com.dominio.projetoecommerce.exception.IdNotFoundException;
import br.com.dominio.projetoecommerce.model.Categoria;
import br.com.dominio.projetoecommerce.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

  @Autowired
  private CategoriaRepository categoriaRepository;

  public Categoria findById(Integer id) {
    return categoriaRepository.findById(id).orElseThrow(() -> new
          IdNotFoundException("Id: " + id + " não encontrado!"));
  }


  public List<Categoria> findAll() {
    if (categoriaRepository.findAll().isEmpty()) {
      throw new EmptyStackException();
    }
    return categoriaRepository.findAll();
  }

  public Categoria post(Categoria categoria) {
    return categoriaRepository.save(categoria);
  }
}
