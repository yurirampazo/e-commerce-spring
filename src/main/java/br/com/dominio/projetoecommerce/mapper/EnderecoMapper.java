package br.com.dominio.projetoecommerce.mapper;

import br.com.dominio.projetoecommerce.domain.Endereco;
import br.com.dominio.projetoecommerce.domain.dto.EnderecoDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {
  EnderecoDto toDto(Endereco endereco);
  Endereco toModel(EnderecoDto enderecoDto);
}
