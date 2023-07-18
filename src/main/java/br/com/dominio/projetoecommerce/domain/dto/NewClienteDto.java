package br.com.dominio.projetoecommerce.domain.dto;


import br.com.dominio.projetoecommerce.domain.Endereco;
import br.com.dominio.projetoecommerce.domain.enums.AppRole;
import br.com.dominio.projetoecommerce.domain.enums.TipoCliente;
import br.com.dominio.projetoecommerce.service.CnpjGroup;
import br.com.dominio.projetoecommerce.service.CpfGroup;
import br.com.dominio.projetoecommerce.service.CustomerGroupSequenceProvider;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@JsonInclude
@AllArgsConstructor
@Builder
@ToString
@GroupSequenceProvider(CustomerGroupSequenceProvider.class)
public class NewClienteDto implements Serializable {

  @NotBlank
  @NotNull
  @Size(max = 150)
  private String nome;

  @NotBlank
  @NotNull
  @Size(max = 255)
  private String email;

  @NotBlank
  @NotNull
  @CPF(groups = CpfGroup.class)
  @CNPJ(groups = CnpjGroup.class)
  private String cpfCnpj;

  @NotNull
  private TipoCliente tipo;

  @NotNull
  private String senha;

  private List<String> roles = new ArrayList<>();

  @NotEmpty
  private List<Endereco> enderecos = new ArrayList<>();

  @NotEmpty
  private Set<String> telefones = new HashSet<>();

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCpfCnpj() {
    return cpfCnpj;
  }

  public void setCpfCnpj(String cpfCnpj) {
    this.cpfCnpj = cpfCnpj;
  }

  public TipoCliente getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = TipoCliente.valueOf(tipo);
  }

  public Set<String> getTelefones() {
    return telefones;
  }

  public void setTelefones(Set<String> telefones) {
    this.telefones = telefones;
  }

  public List<Endereco> getEnderecos() {
    return enderecos;
  }

  public void addEndereco(Endereco endereco) {
    if (endereco != null) {
      boolean contains = this.getEnderecos().contains(endereco);
      if (!contains) {
        this.enderecos.add(endereco);
      }
    }
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public List<String> getRole() {
    return roles;
  }

  public void setRoles(List<AppRole> roles) {
    this.roles = roles.stream().map(AppRole::getName).collect(Collectors.toList());
  }
}
