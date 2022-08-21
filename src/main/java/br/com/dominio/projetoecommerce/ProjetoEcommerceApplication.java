package br.com.dominio.projetoecommerce;

import br.com.dominio.projetoecommerce.model.Categoria;
import br.com.dominio.projetoecommerce.model.Cidade;
import br.com.dominio.projetoecommerce.model.Cliente;
import br.com.dominio.projetoecommerce.model.Endereco;
import br.com.dominio.projetoecommerce.model.Estado;
import br.com.dominio.projetoecommerce.model.Produto;
import br.com.dominio.projetoecommerce.repository.CategoriaRepository;
import br.com.dominio.projetoecommerce.repository.CidadeRepository;
import br.com.dominio.projetoecommerce.repository.ClienteRepository;
import br.com.dominio.projetoecommerce.repository.EnderecoRepository;
import br.com.dominio.projetoecommerce.repository.EstadoRepository;
import br.com.dominio.projetoecommerce.repository.ProdutoRepository;
import br.com.dominio.projetoecommerce.util.TipoCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Arrays;

@SpringBootApplication
public class ProjetoEcommerceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoEcommerceApplication.class, args);
	}

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

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);

		est1.getCidades().add(c1);
		est2.getCidades().add(c2);

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria.silva@gmail.com",
					"43808590815", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco e1 = new Endereco(null, "Rua Flores", "300",
					"Apto 303", "Jardim", "38220834", cli1, c1);

		Endereco e2 = new Endereco(null, "Avenida Matos", "105",
					"Sala 800", "Centro", "38777012", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));


			clienteRepository.save(cli1);
			enderecoRepository.saveAll(Arrays.asList(e1, e2));

	}
}
