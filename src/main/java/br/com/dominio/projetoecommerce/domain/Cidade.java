package br.com.dominio.projetoecommerce.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "cidade")
public class Cidade implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Size(max = 255)
  @NotNull
  @NotBlank
  private String nome;

  @ManyToOne
  @JoinColumn(name = "estado_id")
  private Estado estado;

  @JsonIgnore
  @OneToMany(mappedBy = "cidade")
  private List<Endereco> endereco = new ArrayList<>();

  public Cidade(Integer id, String nome, Estado estado) {
    this.id = id;
    this.nome = nome;
    this.estado = estado;
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Cidade cidade = (Cidade) o;
    return Objects.equals(id, cidade.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
