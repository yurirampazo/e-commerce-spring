package br.com.dominio.projetoecommerce.service;

import br.com.dominio.projetoecommerce.exception.DataIntegrityException;
import br.com.dominio.projetoecommerce.exception.IdNotFoundException;
import br.com.dominio.projetoecommerce.exception.PostNotAllowedException;
import br.com.dominio.projetoecommerce.model.Produto;
import br.com.dominio.projetoecommerce.model.dto.ProdutoDto;
import br.com.dominio.projetoecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

  @Autowired
  private ProdutoRepository produtoRepository;

  public List<ProdutoDto> findAllProdutos() {
    return produtoRepository.findAll().stream().map(Produto::toDto).collect(Collectors.toList());
  }

  public ProdutoDto findProdutoById(Integer id) {
    return Produto.toDto(produtoRepository.findById(id).orElseThrow(() ->
          new IdNotFoundException("Id: " + id + " não encontrado!")));
  }

  public Produto postProduto(Produto produto) {
    boolean exists = produtoRepository.findProdutoByNomeContainingIgnoreCase(produto.getNome()).isPresent();

    if(!exists) {
      return produtoRepository.save(produto);
    } else {
      throw new PostNotAllowedException("Objeto com mesmo nome já exite!");
    }
  }
  public void putProduto(Produto produtoAlterado, Integer id) {
    Produto produto = ProdutoDto.toModel(findProdutoById(id));
    produtoAlterado.getCategorias().forEach(produto::addCategoria);
    produto.setPreco(produtoAlterado.getPreco());
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
