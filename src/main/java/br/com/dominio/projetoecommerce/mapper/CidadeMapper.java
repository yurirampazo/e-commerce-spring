package br.com.dominio.projetoecommerce.mapper;

import br.com.dominio.projetoecommerce.domain.Cidade;
import br.com.dominio.projetoecommerce.domain.dto.CidadeDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CidadeMapper {
  Cidade toModel(CidadeDto cidadeDto);
  CidadeDto toDto(Cidade cidade);
}
