package br.com.dominio.projetoecommerce.mapper;

import br.com.dominio.projetoecommerce.exception.MapToDtoException;
import br.com.dominio.projetoecommerce.exception.MapToModelException;
import br.com.dominio.projetoecommerce.model.Categoria;
import br.com.dominio.projetoecommerce.model.dto.CategoriaDto;

public class CategoriaMapper {

  public static CategoriaDto toDto(Categoria model) {
    if (model == null) {
      throw new MapToDtoException();
    }
    CategoriaDto dto = new CategoriaDto();
    dto.setId(model.getId());
    dto.setNome(model.getNome());
    return dto;
  }

  public static Categoria toModel(CategoriaDto categoriaDto) {
    if (categoriaDto == null) {
      throw new MapToModelException();
    }

    Categoria categoria = new Categoria();
    categoria.setId(categoriaDto.getId());
    categoria.setNome(categoriaDto.getNome());
    return categoria;
  }
}
