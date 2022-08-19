package br.com.dominio.projetoecommerce.service;

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

  public Optional<Produto> findById(Integer id) {
    return produtoRepository.findById(id);
  }

  public Produto postProduto(Produto produto) {
    return produtoRepository.save(produto);
  }


}
