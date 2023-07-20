package br.com.dominio.projetoecommerce.mapper;

import br.com.dominio.projetoecommerce.domain.Endereco;
import br.com.dominio.projetoecommerce.domain.dto.EnderecoDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

  EnderecoMapper INSTANCE = Mappers.getMapper(EnderecoMapper.class);
  Endereco toModel(EnderecoDto enderecoDto);
}
