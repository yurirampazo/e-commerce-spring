package br.com.dominio.projetoecommerce.model.dto;


import br.com.dominio.projetoecommerce.exception.MapToModelException;
import br.com.dominio.projetoecommerce.model.Cliente;
import br.com.dominio.projetoecommerce.model.Endereco;
import br.com.dominio.projetoecommerce.model.Pedido;
import br.com.dominio.projetoecommerce.service.validation.ClienteInsert;
import br.com.dominio.projetoecommerce.util.TipoCliente;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@JsonInclude
@ClienteInsert
public class ClienteDto implements Serializable {

  private Integer id;
  private String nome;
  private String email;
  private String cpfCnpj;
  private Integer tipo;
  private List<Endereco> enderecos = new ArrayList<>();
  private Set<String> telefones = new HashSet<>();

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

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

  public static Cliente toModel(ClienteDto dto) {
    if (dto == null) {
      throw new MapToModelException();
    }


    Cliente model = new Cliente();
    model.setId(dto.getId());
    model.setNome(dto.getNome());
    model.setCpfCnpj(dto.getCpfCnpj());
    model.setEmail(dto.getEmail());
    model.setTipo(dto.getTipo());
    dto.getEnderecos().forEach(model::addEndereco);
    model.addTelefones(dto.getTelefones());
    return model;
  }
}
