package br.com.dominio.projetoecommerce.service;

import br.com.dominio.projetoecommerce.domain.*;
import br.com.dominio.projetoecommerce.domain.dto.PedidoDto;
import br.com.dominio.projetoecommerce.domain.enums.EstadoPagamento;
import br.com.dominio.projetoecommerce.exception.DataIntegrityException;
import br.com.dominio.projetoecommerce.exception.IdNotFoundException;
import br.com.dominio.projetoecommerce.exception.PageNotFoundException;
import br.com.dominio.projetoecommerce.mapper.PedidoMapper;
import br.com.dominio.projetoecommerce.mapper.ProdutoMapper;
import br.com.dominio.projetoecommerce.repository.ItemPedidoRepository;
import br.com.dominio.projetoecommerce.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.EmptyStackException;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PedidoService {

  private final PedidoRepository pedidoRepository;
  private final PagamentoService pagamentoService;
  private final ProdutoService produtoService;
  private final ItemPedidoRepository itemPedidoRepository;
  private final ClienteService clienteService;
  private final EmailService emailService;
  public Page<PedidoDto> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {

    PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction.toUpperCase()), orderBy);
    Page<Pedido> list = pedidoRepository.findAll(pageRequest);
    try {
      return list.map(PedidoMapper.INSTANCE::toDto);
    } catch (EmptyStackException | IndexOutOfBoundsException e) {
      throw new PageNotFoundException(page);
    }
  }

  public PedidoDto findPedidoById(Integer id) {
    Optional<Pedido> pedido = pedidoRepository.findById(id);
    if (pedido.isEmpty()) throw new IdNotFoundException();
    return PedidoMapper.INSTANCE.toDto(pedido.get());
  }

  public void postPedido(PedidoDto pedidoDto) {
    log.info("Started posting order...");
    Pedido pedido =  PedidoMapper.INSTANCE.toModel(pedidoDto);
    Pagamento pagamento;
    if (pedido.getPagamento() instanceof PagamentoComBoleto) {
      log.info("Billet Payment!");
      PagamentoComBoleto pagto = (PagamentoComBoleto) pedido.getPagamento();
      pagto.setDataVencimento(pedido.getInstante().plusWeeks(1L));
      pagto.setEstadoPagamento(EstadoPagamento.PENDENTE);
      pagto.setPedido(pedido);
      pedido.setPagamento(pagto);
      pagamento = pagto;
    } else {
      log.info("Card Payment!");
      PagamentoComCartao pagto = (PagamentoComCartao) pedido.getPagamento();
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

    Optional<Cliente> cli = clienteService.findById(pedido.getCliente().getId());
    if (cli.isEmpty()) {
      throw new IdNotFoundException(pedido.getCliente().getId());
    }
    log.info("Setting client to order!");
    pedido.setCliente(cli.get());

    pagamentoService.postPagamaneto(pagamento);
    pedido = pedidoRepository.save(pedido);
    pedido.getItens().forEach(x -> {
      x.setDesconto(0.0);
      x.setPreco(produtoService.findProdutoById(x.getProduto().getId()).getPreco().doubleValue());
      x.setProduto(ProdutoMapper.INSTANCE.toModel(produtoService.findProdutoById(x.getProduto().getId())));
    });

    itemPedidoRepository.saveAll(pedido.getItens());
    emailService.sendOrderConfirmationEmail(pedido);
    log.info("Posted Order {}", pedido.getId());
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
