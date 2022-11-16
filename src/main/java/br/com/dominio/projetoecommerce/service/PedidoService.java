package br.com.dominio.projetoecommerce.service;

import br.com.dominio.projetoecommerce.enums.EstadoPagamento;
import br.com.dominio.projetoecommerce.exception.DataIntegrityException;
import br.com.dominio.projetoecommerce.exception.IdNotFoundException;
import br.com.dominio.projetoecommerce.exception.PageNotFoundException;
import br.com.dominio.projetoecommerce.mapper.ClienteMapper;
import br.com.dominio.projetoecommerce.mapper.PedidoMapper;
import br.com.dominio.projetoecommerce.mapper.ProdutoMapper;
import br.com.dominio.projetoecommerce.model.Cliente;
import br.com.dominio.projetoecommerce.model.Pagamento;
import br.com.dominio.projetoecommerce.model.PagamentoComBoleto;
import br.com.dominio.projetoecommerce.model.PagamentoComCartao;
import br.com.dominio.projetoecommerce.model.Pedido;
import br.com.dominio.projetoecommerce.model.dto.PedidoDto;
import br.com.dominio.projetoecommerce.repository.ItemPedidoRepository;
import br.com.dominio.projetoecommerce.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.EmptyStackException;

@Service
public class PedidoService {

  @Autowired
  private PedidoRepository pedidoRepository;

  @Autowired
  private PagamentoService pagamentoService;

  @Autowired
  private ProdutoService produtoService;

  @Autowired
  private ItemPedidoRepository itemPedidoRepository;

  @Autowired private ClienteService clienteService;

  @Autowired
  private PedidoMapper pedidoMapper;

  @Autowired private ClienteMapper clienteMapper;

  @Autowired private ProdutoMapper produtoMapper;

  @Autowired private EmailService emailService;

  public Page<PedidoDto> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {

    PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction.toUpperCase()), orderBy);
    Page<Pedido> list = pedidoRepository.findAll(pageRequest);
    try {
      return list.map(pedidoMapper::toDto);
    } catch (EmptyStackException | IndexOutOfBoundsException e) {
      throw new PageNotFoundException(page);
    }
  }

  public PedidoDto findPedidoById(Integer id) {
    return pedidoMapper.toDto(pedidoRepository.findById(id).orElseThrow(() ->
          new IdNotFoundException("Id: " + id + "do pedido não encontrado!")));
  }

  public PedidoDto postPedido(Pedido pedido) {
    Pagamento pagamento;
    if (pedido.getPagamento() instanceof PagamentoComBoleto) {
      PagamentoComBoleto pagto = (PagamentoComBoleto) pedido.getPagamento();
      pagto.setDataVencimento(pedido.getInstante().plusWeeks(1L));
      pagto.setEstadoPagamento(EstadoPagamento.PENDENTE);
      pagto.setPedido(pedido);
      pedido.setPagamento(pagto);
      pagamento = pagto;
    } else {
      PagamentoComCartao pagto = (PagamentoComCartao)  pedido.getPagamento();
      if (((PagamentoComCartao) pedido.getPagamento()).getNumeroDeparcelas() != null) {
      pagto.setNumeroDeparcelas(((PagamentoComCartao) pedido.getPagamento()).getNumeroDeparcelas());
      } else {
        pagto.setNumeroDeparcelas(3);
      }
      pagto.setEstadoPagamento(EstadoPagamento.PENDENTE);
      pagto.setPedido(pedido);
      pedido.setPagamento(pagto);
      pagamento = pagto;
    }

    Cliente cli = clienteMapper.fromNewClientToDto(clienteService.findClienteById(pedido.getCliente().getId()));
    pedido.setCliente(cli);

    pagamentoService.postPagamaneto(pagamento);
    pedido = pedidoRepository.save(pedido);
    pedido.getItens().forEach(x -> {
      x.setDesconto(0.0);
      x.setPreco(produtoService.findProdutoById(x.getProduto().getId()).getPreco().doubleValue());
      x.setProduto(produtoMapper.toModel(produtoService.findProdutoById(x.getProduto().getId())));
    });

    itemPedidoRepository.saveAll(pedido.getItens());
    emailService.sendOrderConfirmationEmail(pedido);
    return pedidoMapper.toDto(pedidoRepository.save(pedido));
  }

  public void putPedido(Integer id, Pedido pedidoAlterado) {
    Pedido pedido = pedidoRepository.findById(id).orElse(null);
    assert pedido != null;
    pedido.setCliente(pedidoAlterado.getCliente());
    pedido.setPagamento(pedidoAlterado.getPagamento());
    pedido.setEnderecoDeEntrega(pedidoAlterado.getEnderecoDeEntrega());
    pedidoRepository.save(pedido);
  }

  public void deletePedido(Integer id) {
    findPedidoById(id);
    try {
      pedidoRepository.deleteById(id);
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException();
    }
  }
}
