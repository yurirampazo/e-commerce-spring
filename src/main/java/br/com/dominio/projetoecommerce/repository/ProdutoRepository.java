package br.com.dominio.projetoecommerce.repository;

import br.com.dominio.projetoecommerce.model.Categoria;
import br.com.dominio.projetoecommerce.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
  Optional<Produto> findProdutoByNomeContainingIgnoreCase(String nome);

  @Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat" +
        " WHERE obj.nome LIKE %:nome% AND cat IN :categorias ")
  Page<Produto> search(@Param("nome") String nome,@Param("categorias") List<Categoria> categorias, Pageable pageRequest);

  //OU
  Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageRequest);
//Nesse caso o método deveria ser renomeado na camada de serviço também
}
