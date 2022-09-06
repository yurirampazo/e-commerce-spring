package br.com.dominio.projetoecommerce.service;

import br.com.dominio.projetoecommerce.exception.DataIntegrityException;
import br.com.dominio.projetoecommerce.exception.IdNotFoundException;
import br.com.dominio.projetoecommerce.exception.PostNotAllowedException;
import br.com.dominio.projetoecommerce.model.Categoria;
import br.com.dominio.projetoecommerce.model.dto.CategoriaDto;
import br.com.dominio.projetoecommerce.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.EmptyStackException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

  @Autowired
  private CategoriaRepository categoriaRepository;

  @Autowired
  private ProdutoService produtoService;

  public CategoriaDto findCategoriaById(Integer id) {
    return Categoria.toDto(categoriaRepository.findById(id).orElseThrow(() -> new
          IdNotFoundException(id)));
  }

  public List<CategoriaDto> findAllCategorias() {
    if (categoriaRepository.findAll().isEmpty()) {
      throw new EmptyStackException();
    }
    return categoriaRepository.findAll().stream().map(Categoria::toDto).collect(Collectors.toList());
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
    Categoria categoria = CategoriaDto.toModel(findCategoriaById(id));
    categoria.setNome(categoriaAlterada.getNome());
    categoriaRepository.save(categoria);
  }

  public void deleteCategoriaById(Integer id) {
    findCategoriaById(id);
    try {
      categoriaRepository.deleteById(id);
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos associados!");
    }
  }
}
