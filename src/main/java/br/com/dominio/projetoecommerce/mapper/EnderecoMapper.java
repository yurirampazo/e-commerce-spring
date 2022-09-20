package br.com.dominio.projetoecommerce.mapper;

import br.com.dominio.projetoecommerce.exception.MapToDtoException;
import br.com.dominio.projetoecommerce.exception.MapToModelException;
import br.com.dominio.projetoecommerce.model.Endereco;
import br.com.dominio.projetoecommerce.model.dto.EnderecoDto;

public class EnderecoMapper {
  public static Endereco toModel(EnderecoDto dto) {
    if (dto == null) {
      throw new MapToModelException();
    }

    Endereco model = new Endereco();
    model.setId(dto.getId());
    model.setBairro(dto.getBairro());
    model.setCep(dto.getCep());
    model.setCidade(CidadeMapper.toModel(dto.getCidade()));
    model.setNumero(dto.getNumero());
    model.setComplemento(dto.getComplemento());
    return model;
  }

  public static EnderecoDto toDto(Endereco model) {
    if (model == null) {
      throw new MapToDtoException();
    }

    EnderecoDto dto = new EnderecoDto();
    dto.setId(model.getId());
    dto.setCidade(CidadeMapper.toDto(model.getCidade()));
    dto.setLogradouro(model.getLogradouro());
    dto.setNumero(model.getNumero());
    dto.setBairro(model.getBairro());
    dto.setCep(model.getCep());
    dto.setComplemento(model.getComplemento());
    return dto;
  }
}
