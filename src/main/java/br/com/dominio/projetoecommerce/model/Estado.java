package br.com.dominio.projetoecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "estado")
public class Estado implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank
  @NotNull
  @Size(max = 255)
  private String nome;

  @JsonIgnore
  @OneToMany(mappedBy = "estado")
  private List<Cidade> cidades = new ArrayList<>();

  public Estado(Integer id, String nome) {
    this.id = id;
    this.nome = nome;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void addCidade(Cidade cidade) {
    boolean contains = this.cidades.contains(cidade);

    if (!contains) {
      cidades.add(cidade);
    }

   }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Estado estado = (Estado) o;
    return Objects.equals(id, estado.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
