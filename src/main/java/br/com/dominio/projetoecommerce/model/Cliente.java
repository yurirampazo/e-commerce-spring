package br.com.dominio.projetoecommerce.model;


import br.com.dominio.projetoecommerce.util.TipoCliente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Cliente implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String nome;
  private String email;
  private String cpfCnpj;
  private Integer tipo;

  @JsonIgnoreProperties("cliente")
  @OneToMany(mappedBy = "cliente")
  private List<Endereco> enderecos = new ArrayList<>();

  @ElementCollection
  @CollectionTable(name = "telefone")
  private Set<String> telefones = new HashSet<>();

  public Cliente(Integer id, String nome, String email, String cpfCnpj, TipoCliente tipo) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.cpfCnpj = cpfCnpj;
    this.tipo = tipo.getTipo();
  }


  public Integer getId() {
    return id;
  }

  public String getNome() {
    return nome;
  }

  public String getEmail() {
    return email;
  }

  public String getCpfCnpj() {
    return cpfCnpj;
  }

  public TipoCliente getTipo() {
    return TipoCliente.toEnum(tipo);
  }

  public List<Endereco> getEnderecos() {
    return enderecos;
  }

  public Set<String> getTelefones() {
    return telefones;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setCpfCnpj(String cpfCnpj) {
    this.cpfCnpj = cpfCnpj;
  }

  public void setTipo(TipoCliente tipo) {
    this.tipo = tipo.getTipo();
  }

  public void addEndereco(Endereco endereco) {
    endereco = enderecos.contains(endereco) ? null : endereco;

    if (endereco != null) {
      enderecos.add(endereco);
    }
  }

  public void addTelefone(String telefone) {
    telefone = telefones.contains(telefone) ? null : telefone;

    if (telefone != null) {
      telefones.add(telefone);
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Cliente cliente = (Cliente) o;
    return Objects.equals(id, cliente.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
