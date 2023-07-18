package br.com.dominio.projetoecommerce.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonInclude
public class EnderecoDto {


  private Integer id;
  private String logradouro;
  private String numero;
  private String complemento;
  private String bairro;
  private String cep;
  private NewClienteDto cliente;
  private CidadeDto cidade;

  public void setId(Integer id) {
    this.id = id;
  }

  public void setLogradouro(String logradouro) {
    this.logradouro = logradouro;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public void setComplemento(String complemento) {
    this.complemento = complemento;
  }

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public void setCliente(NewClienteDto cliente) {
    this.cliente = cliente;
  }

  public void setCidade(CidadeDto cidade) {
    this.cidade = cidade;
  }

}
