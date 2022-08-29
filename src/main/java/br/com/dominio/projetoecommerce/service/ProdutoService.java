package br.com.dominio.projetoecommerce.service;

import br.com.dominio.projetoecommerce.exception.IdNotFoundException;
import br.com.dominio.projetoecommerce.exception.PostNotAllowedException;
import br.com.dominio.projetoecommerce.model.Produto;
import br.com.dominio.projetoecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

  @Autowired
  private ProdutoRepository produtoRepository;

  public List<Produto> findAllProdutos() {
    return produtoRepository.findAll();
  }

  public Produto findProdutoById(Integer id) {
    return produtoRepository.findById(id).orElseThrow(() ->
          new IdNotFoundException("Id: " + id + " não encontrado!"));
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
    Produto produto = findProdutoById(id);
    produtoAlterado.getCategorias().forEach(produto::addCategoria);
    produto.setPreco(produtoAlterado.getPreco());
    produtoRepository.save(produto);
  }

  public void deleteProduto(Integer id) {
    findProdutoById(id);
    produtoRepository.deleteById(id);
  }
}
