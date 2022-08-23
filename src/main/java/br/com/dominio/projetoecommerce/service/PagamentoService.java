package br.com.dominio.projetoecommerce.service;

import br.com.dominio.projetoecommerce.exception.IdNotFoundException;
import br.com.dominio.projetoecommerce.exception.PostNotAllowedException;
import br.com.dominio.projetoecommerce.model.Pagamento;
import br.com.dominio.projetoecommerce.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoService {

  @Autowired
  private PagamentoRepository pagamentoRepository;

  public List<Pagamento> findAll() {
    return pagamentoRepository.findAll();
  }

  public Pagamento findById(Integer id) {
    return pagamentoRepository.findById(id).orElseThrow(() ->
          new IdNotFoundException(id));
  }

  public <T extends Pagamento> T postPedido(T pagamento) {
    boolean exists = pagamentoRepository.findById(pagamento.getId()).isPresent();

    if(!exists) {
      return pagamentoRepository.save(pagamento);
    } else {
      throw new PostNotAllowedException("Pagamento duplicado!");
    }
  }
}
