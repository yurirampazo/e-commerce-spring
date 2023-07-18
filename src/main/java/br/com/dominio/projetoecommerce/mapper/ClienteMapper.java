package br.com.dominio.projetoecommerce.mapper;

import br.com.dominio.projetoecommerce.domain.Cliente;
import br.com.dominio.projetoecommerce.domain.dto.ClienteDto;
import br.com.dominio.projetoecommerce.domain.dto.NewClienteDto;
import br.com.dominio.projetoecommerce.domain.enums.TipoCliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
  ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);
  NewClienteDto newClienteDto(Cliente cliente);
  ClienteDto toDto(Cliente cliente);
  Cliente toModel(ClienteDto clienteDto);
  Cliente fromNewClientToDto(NewClienteDto newClienteDto);
}
