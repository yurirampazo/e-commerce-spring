package br.com.dominio.projetoecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
