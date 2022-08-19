package br.com.dominio.projetoecommerce.service;

import br.com.dominio.projetoecommerce.exception.IdNotFoundException;
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

  public List<Produto> findAll() {
    return produtoRepository.findAll();
  }

  public Produto findById(Integer id) {
    return produtoRepository.findById(id).orElseThrow(() ->
          new IdNotFoundException("Id: " + id + " não encontrado!"));
  }

  public Produto postProduto(Produto produto) {
    return produtoRepository.save(produto);
  }


}
