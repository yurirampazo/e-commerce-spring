package br.com.dominio.projetoecommerce;

import br.com.dominio.projetoecommerce.model.Categoria;
import br.com.dominio.projetoecommerce.model.Produto;
import br.com.dominio.projetoecommerce.repository.CategoriaRepository;
import br.com.dominio.projetoecommerce.repository.ProdutoRepository;
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

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");



		Produto p1 = new Produto(null, "Computador", BigDecimal.valueOf(3800.00));
		Produto p2 = new Produto(null, "MX- Master (Mouse)", BigDecimal.valueOf(500.00));

		cat1.getProdutos().addAll(Arrays.asList(p1, p2));
		cat2.getProdutos().add(p1);

		p1.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p2.getCategorias().add(cat2);

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2));


	}
}
