package br.com.dominio.projetoecommerce.model;


import br.com.dominio.projetoecommerce.enums.Perfil;
import br.com.dominio.projetoecommerce.enums.TipoCliente;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "cliente")
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

  @JsonIgnoreProperties("cliente")
  @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
  private List<Endereco> enderecos = new ArrayList<>();

  @ElementCollection
  @CollectionTable(name = "telefone")
  private Set<String> telefones = new HashSet<>();

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "PERFIS")
  private Set<Integer> perfis = new HashSet<>();

  @OneToMany(mappedBy = "cliente")
  List<Pedido> pedidos = new ArrayList<>();

  public Cliente(Integer id, String nome, String email, String cpfCnpj, TipoCliente tipo) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.cpfCnpj = cpfCnpj;
    this.tipo = tipo.getTipo();
    addPerfil(Perfil.CLIENTE);
  }

  public Cliente(Integer id, String nome, String email, String cpfCnpj, TipoCliente tipo, String senha) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.cpfCnpj = cpfCnpj;
    this.tipo = tipo.getTipo();
    this.senha = senha;
    addPerfil(Perfil.CLIENTE);
  }

  public Cliente() {
    addPerfil(Perfil.CLIENTE);
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

  public Set<Perfil> getPerfis() {
    return perfis.stream().map(Perfil::toEnum).collect(Collectors.toSet());
  }

  public void addPerfil(Perfil perfil) {
    perfis.add(perfil.getTipo());
  }

  @JsonIgnore
  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
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
