package br.com.dominio.projetoecommerce.service;

import br.com.dominio.projetoecommerce.exception.DataIntegrityException;
import br.com.dominio.projetoecommerce.exception.IdNotFoundException;
import br.com.dominio.projetoecommerce.exception.PageNotFoundException;
import br.com.dominio.projetoecommerce.exception.PostNotAllowedException;
import br.com.dominio.projetoecommerce.mapper.ProdutoMapper;
import br.com.dominio.projetoecommerce.domain.Categoria;
import br.com.dominio.projetoecommerce.domain.Produto;
import br.com.dominio.projetoecommerce.domain.dto.ProdutoDto;
import br.com.dominio.projetoecommerce.repository.CategoriaRepository;
import br.com.dominio.projetoecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.EmptyStackException;
import java.util.List;

@Service
public class ProdutoService {

  @Autowired
  private ProdutoRepository produtoRepository;

  @Autowired
  private CategoriaRepository categoriaRepository;

  public Page<ProdutoDto> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
    PageRequest pageRequest = PageRequest.of(page, linesPerPage,
          Sort.Direction.valueOf(direction.toUpperCase()), orderBy);
    Page<Produto> list = produtoRepository.findAll(pageRequest);
    try {
      return list.map(ProdutoMapper.INSTANCE::toDto);
    } catch (EmptyStackException | IndexOutOfBoundsException e) {
      throw new PageNotFoundException(page);
    }
  }

  public Page<ProdutoDto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String direction, String orderBy) {
    PageRequest pageRequest = PageRequest.of(page, linesPerPage,
          Sort.Direction.valueOf(direction.toUpperCase()), orderBy);
    List<Categoria> categorias = categoriaRepository.findAllById(ids);
    Page<Produto> list = produtoRepository.search(nome, categorias, pageRequest);
    try {
      return list.map(ProdutoMapper.INSTANCE::toDto);
    } catch (EmptyStackException | IndexOutOfBoundsException e) {
      throw new PageNotFoundException(page);
    }
  }

  public ProdutoDto findProdutoById(Integer id) {
    return ProdutoMapper.INSTANCE.toDto(produtoRepository.findById(id).orElseThrow(() ->
          new IdNotFoundException("Id: " + id + " não encontrado!")));
  }

  public Produto postProduto(Produto produto) {
    boolean exists = produtoRepository.findProdutoByNomeContainingIgnoreCase(produto.getNome()).isPresent();

    if (!exists) {
      return produtoRepository.save(produto);
    } else {
      throw new PostNotAllowedException("Objeto com mesmo nome já exite!");
    }
  }

  public void putProduto(Produto produtoAlterado, Integer id) {
    Produto produto = ProdutoMapper.INSTANCE.toModel(findProdutoById(id));
    produtoAlterado.getCategorias().forEach(produto::addCategoria);
    produto.setPreco(BigDecimal.valueOf(produtoAlterado.getPreco()));
    produtoRepository.save(produto);
  }

  public void deleteProduto(Integer id) {
    findProdutoById(id);
    try {
      produtoRepository.deleteById(id);
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException();
    }
  }
}
