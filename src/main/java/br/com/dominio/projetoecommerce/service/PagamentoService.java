package br.com.dominio.projetoecommerce.service;

import br.com.dominio.projetoecommerce.exception.DataIntegrityException;
import br.com.dominio.projetoecommerce.exception.IdNotFoundException;
import br.com.dominio.projetoecommerce.exception.PostNotAllowedException;
import br.com.dominio.projetoecommerce.model.Pagamento;
import br.com.dominio.projetoecommerce.model.PagamentoComBoleto;
import br.com.dominio.projetoecommerce.model.PagamentoComCartao;
import br.com.dominio.projetoecommerce.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoService {

  @Autowired
  private PagamentoRepository pagamentoRepository;

  public List<Pagamento> findAllPagamentos() {
    return pagamentoRepository.findAll();
  }

  public Pagamento findPagamentoById(Integer id) {
    return pagamentoRepository.findById(id).orElseThrow(() ->
          new IdNotFoundException(id));
  }


  public Pagamento postPagamaneto(Pagamento pagamento) {
    return pagamentoRepository.save(pagamento);
  }

  public void putPagamento(Pagamento pagamentoAlterado, Integer id) {
    Pagamento pagamento = findPagamentoById(id);

    pagamento.setPedido(pagamentoAlterado.getPedido());
    pagamentoAlterado.setEstadoPagamento(pagamentoAlterado.getEstadoPagamento());

    if (pagamentoAlterado instanceof PagamentoComBoleto) {
      Pagamento pagamentoComBoleto = new PagamentoComBoleto(pagamento.getId(),
            pagamento.getEstadoPagamento(), pagamento.getPedido(),
            ((PagamentoComBoleto) pagamentoAlterado).getDataPagamento(),
            ((PagamentoComBoleto) pagamentoAlterado).getDataVencimento());
      pagamentoRepository.save(pagamentoComBoleto);

    } else if (pagamentoAlterado instanceof PagamentoComCartao) {
      Pagamento pagamentoComCartao = new PagamentoComCartao(pagamento.getId(),
            pagamento.getEstadoPagamento(), pagamento.getPedido(),
            ((PagamentoComCartao) pagamentoAlterado).getNumeroDeparcelas());
      pagamentoRepository.save(pagamentoComCartao);
    } else {
      pagamentoRepository.save(pagamento);
    }
  }

  public void deletePagamento(Integer id) {
    findPagamentoById(id);
    try {
      pagamentoRepository.deleteById(id);
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException();
    }
  }
}
