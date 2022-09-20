package br.com.dominio.projetoecommerce.mapper;

import br.com.dominio.projetoecommerce.exception.MapToDtoException;
import br.com.dominio.projetoecommerce.exception.MapToModelException;
import br.com.dominio.projetoecommerce.model.Cliente;
import br.com.dominio.projetoecommerce.model.dto.NewClienteDto;

public class ClienteMapper {

  public static NewClienteDto toDto(Cliente model) {
    if (model == null) {
      throw new MapToDtoException();
    }
    NewClienteDto dto = new NewClienteDto();
    dto.setNome(model.getNome());
    dto.setCpfCnpj(model.getCpfCnpj());
    dto.setEmail(model.getEmail());
    dto.setTipo(model.getTipo());
    dto.setTelefones(model.getTelefones());
    model.getEnderecos().forEach(dto::addEndereco);
    return dto;
  }

  public static Cliente toModel(NewClienteDto dto) {
    if (dto == null) {
      throw new MapToModelException();
    }

    Cliente model = new Cliente();
    model.setNome(dto.getNome());
    model.setCpfCnpj(dto.getCpfCnpj());
    model.setEmail(dto.getEmail());
    model.setTipo(dto.getTipo());
    dto.getEnderecos().forEach(model::addEndereco);
    model.addTelefones(dto.getTelefones());
    return model;
  }
}
