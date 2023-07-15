package br.com.dominio.projetoecommerce.mapper;

import br.com.dominio.projetoecommerce.model.Categoria;
import br.com.dominio.projetoecommerce.model.dto.CategoriaDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface CategoriaMapper {

  CategoriaMapper INSTANCE = Mappers.getMapper(CategoriaMapper.class);
  Categoria toModel(CategoriaDto categoriaDto);
  CategoriaDto toDto(Categoria categoria);

}
