package br.com.dominio.projetoecommerce.service;

import br.com.dominio.projetoecommerce.exception.IdNotFoundException;
import br.com.dominio.projetoecommerce.exception.PostNotAllowedException;
import br.com.dominio.projetoecommerce.model.Categoria;
import br.com.dominio.projetoecommerce.model.Produto;
import br.com.dominio.projetoecommerce.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

@Service
public class CategoriaService {

  @Autowired
  private CategoriaRepository categoriaRepository;

  @Autowired
  private ProdutoService produtoService;

  public Categoria findCategoriaById(Integer id) {
    return categoriaRepository.findById(id).orElseThrow(() -> new
          IdNotFoundException(id));
  }

  public List<Categoria> findAllCategorias() {
    if (categoriaRepository.findAll().isEmpty()) {
      throw new EmptyStackException();
    }
    return categoriaRepository.findAll();
  }

  public Categoria postCategoria(Categoria categoria) {
    boolean exists = categoriaRepository.findCategoriaByNomeContainingIgnoreCase(categoria.getNome()).isPresent();

    if (!exists) {
      return categoriaRepository.save(categoria);
    } else {
      throw new PostNotAllowedException("Objeto com mesmo nome já exite!");
    }
  }

  public void putCategoria(Categoria categoriaAlterada, Integer id) {
    Categoria categoria = findCategoriaById(id);
    categoria.setNome(categoriaAlterada.getNome());
    categoriaRepository.save(categoria);
  }
  public void deleteCategoriaById(Integer id) {
    findCategoriaById(id);
    categoriaRepository.deleteById(id);
  }

}
