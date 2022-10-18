package br.com.dominio.projetoecommerce.mapper;

import br.com.dominio.projetoecommerce.model.Cliente;
import br.com.dominio.projetoecommerce.model.dto.ClienteDto;
import br.com.dominio.projetoecommerce.model.dto.NewClienteDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
  NewClienteDto newClienteDto(Cliente cliente);
  ClienteDto toDto(Cliente cliente);
  Cliente toModel(ClienteDto clienteDto);
  Cliente fromNewClientToDto(NewClienteDto newClienteDto);

}
