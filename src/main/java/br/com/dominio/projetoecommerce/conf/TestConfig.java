package br.com.dominio.projetoecommerce.conf;

import br.com.dominio.projetoecommerce.model.Categoria;
import br.com.dominio.projetoecommerce.model.Cidade;
import br.com.dominio.projetoecommerce.model.Cliente;
import br.com.dominio.projetoecommerce.model.Endereco;
import br.com.dominio.projetoecommerce.model.Estado;
import br.com.dominio.projetoecommerce.model.ItemPedido;
import br.com.dominio.projetoecommerce.model.Pagamento;
import br.com.dominio.projetoecommerce.model.PagamentoComBoleto;
import br.com.dominio.projetoecommerce.model.PagamentoComCartao;
import br.com.dominio.projetoecommerce.model.Pedido;
import br.com.dominio.projetoecommerce.model.Produto;
import br.com.dominio.projetoecommerce.repository.CategoriaRepository;
import br.com.dominio.projetoecommerce.repository.CidadeRepository;
import br.com.dominio.projetoecommerce.repository.ClienteRepository;
import br.com.dominio.projetoecommerce.repository.EnderecoRepository;
import br.com.dominio.projetoecommerce.repository.EstadoRepository;
import br.com.dominio.projetoecommerce.repository.ItemPedidoRepository;
import br.com.dominio.projetoecommerce.repository.PagamentoRepository;
import br.com.dominio.projetoecommerce.repository.PedidoRepository;
import br.com.dominio.projetoecommerce.repository.ProdutoRepository;
import br.com.dominio.projetoecommerce.util.EstadoPagamento;
import br.com.dominio.projetoecommerce.util.TipoCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

  @Autowired
  private CategoriaRepository categoriaRepository;

  @Autowired
  private ProdutoRepository produtoRepository;

  @Autowired
  private CidadeRepository cidadeRepository;

  @Autowired
  private EstadoRepository estadoRepository;

  @Autowired
  private ClienteRepository clienteRepository;

  @Autowired
  private EnderecoRepository enderecoRepository;

  @Autowired
  private PedidoRepository pedidoRepository;

  @Autowired
  private PagamentoRepository pagamentoRepository;

  @Autowired
  private ItemPedidoRepository itemPedidoRepository;

  @Override
  public void run(String... args) throws Exception {
    Categoria cat1 = new Categoria(null, "Informatica");
    Categoria cat2 = new Categoria(null, "Escritório");

    Produto p1 = new Produto(null, "Computador", BigDecimal.valueOf(3800.00));
    Produto p2 = new Produto(null, "MX- Master (Mouse)", BigDecimal.valueOf(500.00));

    cat1.getProdutos().addAll(Arrays.asList(p1, p2));
    cat2.getProdutos().add(p1);

    p1.getCategorias().addAll(Arrays.asList(cat1, cat2));
    p2.getCategorias().add(cat2);

    categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
    produtoRepository.saveAll(Arrays.asList(p1, p2));

    Estado est1 = new Estado(null, "Minas Gerais");
    Estado est2 = new Estado(null, "São Paulo");

    Cidade c1 = new Cidade(null, "Uberlandia", est1);
    Cidade c2 = new Cidade(null, "São Paulo", est2);

    est1.getCidades().add(c1);
    est2.getCidades().add(c2);


    estadoRepository.saveAll(Arrays.asList(est1, est2));
    cidadeRepository.saveAll(Arrays.asList(c1, c2));

    Cliente cli1 = new Cliente(null, "Maria Silva", "maria.silva@gmail.com",
          "43808590815", TipoCliente.PESSOAFISICA);
    cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

    Cliente cli2 = new Cliente(null, "Silva Marcos", "marcos.silva@gmail.com",
          "43808590816", TipoCliente.PESSOAFISICA);
    cli2.getTelefones().addAll(Arrays.asList("27363325", "93838395"));

    Endereco e1 = new Endereco(null, "Rua Flores", "300",
          "Apto 303", "Jardim", "38220834", cli1, c1);

    Endereco e2 = new Endereco(null, "Avenida Matos", "105",
          "Sala 800", "Centro", "38777012", cli1, c2);

    Endereco e3 = new Endereco(null, "Avenida Longa", "110",
          "N/A", "Centro", "38777013", cli2, c2);

    cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

    clienteRepository.saveAll(Arrays.asList(cli1, cli2));
    enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));

    Pedido ped1 = new Pedido(null, LocalDateTime.of(2022, Month.AUGUST,
          22, 13, 45), cli1, e1);
    Pedido ped2 = new Pedido(null, LocalDateTime.of(2022, Month.AUGUST,
          15, 22, 10), cli2, e3);

    Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
    Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2,
          LocalDateTime.of(2022, Month.AUGUST, 19, 12, 23),
          LocalDateTime.of(2022, Month.AUGUST, 20, 15, 45));

    ped1.setPagamento(pag1);
    ped2.setPagamento(pag2);

    pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
    pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));

    ItemPedido ip1 = new ItemPedido(ped1, p1, BigDecimal.valueOf(0.00), 1, BigDecimal.valueOf(2000.00));
    ItemPedido ip2 = new ItemPedido(ped1, p2, BigDecimal.valueOf(0.00), 2, BigDecimal.valueOf(80.00));
    ItemPedido ip3 = new ItemPedido(ped2, p2, BigDecimal.valueOf(100.00), 1, BigDecimal.valueOf(800.00));

    ped1.getItens().addAll(Arrays.asList(ip1, ip2));
    ped2.getItens().add(ip3);

    p1.getItens().add(ip1);
    p2.getItens().addAll(Arrays.asList(ip2, ip3));

    itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

  }
}
