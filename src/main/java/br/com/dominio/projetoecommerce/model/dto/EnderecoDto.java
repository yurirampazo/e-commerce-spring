package br.com.dominio.projetoecommerce.model.dto;

import br.com.dominio.projetoecommerce.exception.MapToModelException;
import br.com.dominio.projetoecommerce.model.Cidade;
import br.com.dominio.projetoecommerce.model.Cliente;
import br.com.dominio.projetoecommerce.model.Endereco;
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
  private ClienteDto cliente;
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

  public void setCliente(ClienteDto cliente) {
    this.cliente = cliente;
  }

  public void setCidade(CidadeDto cidade) {
    this.cidade = cidade;
  }

  public static Endereco toModel(EnderecoDto dto) {
    if (dto == null) {
      throw new MapToModelException();
    }

    Endereco model = new Endereco();
    model.setId(dto.getId());
    model.setBairro(dto.getBairro());
    model.setCep(dto.getCep());
    model.setCidade(CidadeDto.toModel(dto.getCidade()));
    model.setNumero(dto.getNumero());
    model.setComplemento(dto.getComplemento());
    return model;
  }
}
