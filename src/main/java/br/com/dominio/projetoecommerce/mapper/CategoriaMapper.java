package br.com.dominio.projetoecommerce.mapper;

import br.com.dominio.projetoecommerce.model.Categoria;
import br.com.dominio.projetoecommerce.model.dto.CategoriaDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CategoriaMapper {
  Categoria toModel(CategoriaDto categoriaDto);
  CategoriaDto toDto(Categoria categoria);

}
