package br.com.dominio.projetoecommerce.model.dto;


import br.com.dominio.projetoecommerce.exception.MapToModelException;
import br.com.dominio.projetoecommerce.model.Cliente;
import br.com.dominio.projetoecommerce.model.Endereco;
import br.com.dominio.projetoecommerce.model.Pedido;
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
public class ClienteDto implements Serializable {

  private Integer id;
  private String nome;
  private String email;
  private String cpfCnpj;
  private Integer tipo;
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
    return model;
  }
}
