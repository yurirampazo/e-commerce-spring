package br.com.dominio.projetoecommerce.service;

import br.com.dominio.projetoecommerce.exception.DataIntegrityException;
import br.com.dominio.projetoecommerce.exception.IdNotFoundException;
import br.com.dominio.projetoecommerce.exception.PageNotFoundException;
import br.com.dominio.projetoecommerce.exception.PostNotAllowedException;
import br.com.dominio.projetoecommerce.mapper.CategoriaMapper;
import br.com.dominio.projetoecommerce.model.Categoria;
import br.com.dominio.projetoecommerce.model.dto.CategoriaDto;
import br.com.dominio.projetoecommerce.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.EmptyStackException;

@Service
public class CategoriaService {

  @Autowired
  private CategoriaRepository categoriaRepository;


  public Page<CategoriaDto> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
    PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction.toUpperCase()), orderBy);
    try {
      Page<Categoria> list = categoriaRepository.findAll(pageRequest);
      return list.map(CategoriaMapper::toDto);
    } catch (EmptyStackException | IndexOutOfBoundsException e) {
      throw new PageNotFoundException(page);
    }
  }

  public CategoriaDto findCategoriaById(Integer id) {
    return CategoriaMapper.toDto(categoriaRepository.findById(id).orElseThrow(() -> new
          IdNotFoundException(id)));
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
    Categoria categoria = CategoriaMapper.toModel(findCategoriaById(id));
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
