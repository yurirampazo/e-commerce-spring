package br.com.dominio.projetoecommerce.domain;


import br.com.dominio.projetoecommerce.domain.enums.AppRole;
import br.com.dominio.projetoecommerce.domain.enums.TipoCliente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "cliente")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Cliente implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Size(max = 255)
  private String nome;

  @Email
  @Size(max = 300)
  private String email;
  private String cpfCnpj;
  private Integer tipo;
  private String senha;

 @ElementCollection(fetch = FetchType.EAGER)
 @CollectionTable(name = "roles")
  private Set<AppRole> roles = new HashSet<>();

  @JsonIgnoreProperties("cliente")
  @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
  private List<Endereco> enderecos = new ArrayList<>();

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "telefone")
  private Set<String> telefones = new HashSet<>();

  @OneToMany(mappedBy = "cliente")
  List<Pedido> pedidos = new ArrayList<>();

  public Cliente(Integer id, String nome, String email, String cpfCnpj, TipoCliente tipo, String senha) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.cpfCnpj = cpfCnpj;
    this.tipo = tipo.getTipo();
    this.senha = senha;
    roles.add(AppRole.USER);
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

  public void addTelefones(Set<String> telefones) {
    this.telefones.addAll(telefones);
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

  public void addPedido(Pedido pedido) {
    pedido = pedidos.contains(pedido) ? null : pedido;

    if (pedido != null) {
      pedidos.add(pedido);
    }
  }

  public List<Pedido> getPedidos() {
    return pedidos;
  }

  public void addTelefone(String telefone) {
    telefone = telefones.contains(telefone) ? null : telefone;

    if (telefone != null) {
      telefones.add(telefone);
    }
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public Set<AppRole> getRoles() {
    return roles;
  }

  public void addRole(AppRole role) {
    roles.add(role);
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
