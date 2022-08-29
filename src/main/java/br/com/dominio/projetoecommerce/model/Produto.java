package br.com.dominio.projetoecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "produto")
@Getter
@NoArgsConstructor
public class Produto implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotNull
  @NotBlank
  @Size(max = 255)
  private String nome;

  @Min(0)
  @NotNull
  private BigDecimal preco;

  @ManyToMany
  @JoinTable(
        name = "PRODUTO_CATEGORIA",
        joinColumns = @JoinColumn(name = "produto_id"),
        inverseJoinColumns = @JoinColumn(name = "categoria_id")
  )
  @NotNull
  @NotEmpty
  private List<Categoria> categorias = new ArrayList<>();

  @JsonIgnore
  @OneToMany(mappedBy = "id.produto")
  private Set<ItemPedido> itens = new HashSet<>();

  public Produto(Integer id, String nome, BigDecimal preco, Categoria categoria) {
    this.id = id;
    this.nome = nome;
    this.preco = preco;
    this.categorias.add(categoria);
  }

  public Produto(Integer id, String nome, BigDecimal preco) {
    this.id = id;
    this.nome = nome;
    this.preco = preco;
  }
  public void setId(Integer id) {
    this.id = id;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setPreco(BigDecimal preco) {
    this.preco = preco;
  }

  public void addCategoria(Categoria categoria) {
    Categoria c1 = categorias.contains(categoria) ? null : categoria;
    if (c1 != null) {
      categorias.add(categoria);
    }
  }
  public void addItens(ItemPedido itemPedido) {
    itens.add(itemPedido);
  }

  @JsonIgnore
  List<Pedido> getPedidos() {
    List<Pedido> lista = new ArrayList<>();
    itens.forEach(x -> lista.add(x.getPedido()));
    return lista;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Produto produto = (Produto) o;
    return Objects.equals(nome, produto.nome);
  }

  @Override
  public int hashCode() {
    return Objects.hash(nome);
  }
}
