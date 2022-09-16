package br.com.dominio.projetoecommerce.model;


import br.com.dominio.projetoecommerce.exception.MapToDtoException;
import br.com.dominio.projetoecommerce.model.dto.CategoriaDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Getter
@NoArgsConstructor
@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Size(max = 33, min = 5)
  @NotBlank
  @NotNull
  private String nome;

  @JsonIgnore
  @ManyToMany(mappedBy = "categorias")
  private List<Produto> produtos = new ArrayList<>();

  public Categoria(Integer id, String nome) {
    this.id = id;
    this.nome = nome;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void addProduto(Produto produto) {
    Produto p1;
    p1 = produtos.contains(produto) ? null : produto;

    if (p1 != null) {
      produtos.add(produto);
    }
  }

  public static CategoriaDto toDto(Categoria model) {
    if (model == null) {
      throw new MapToDtoException();
    }
    CategoriaDto dto = new CategoriaDto();
    dto.setId(model.getId());
    dto.setNome(model.getNome());
    return dto;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Categoria categoria = (Categoria) o;
    return Objects.equals(id, categoria.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
