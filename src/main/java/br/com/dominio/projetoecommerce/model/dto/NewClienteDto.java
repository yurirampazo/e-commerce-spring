package br.com.dominio.projetoecommerce.model.dto;


import br.com.dominio.projetoecommerce.model.Endereco;
import br.com.dominio.projetoecommerce.service.validation.ClienteInsert;
import br.com.dominio.projetoecommerce.util.TipoCliente;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@JsonInclude
@ClienteInsert
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
  private String cpfCnpj;

  @NotNull
  private Integer tipo;

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
    return TipoCliente.toEnum(tipo);
  }

  public void setTipo(TipoCliente tipo) {
    this.tipo = tipo.getTipo();
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
}
