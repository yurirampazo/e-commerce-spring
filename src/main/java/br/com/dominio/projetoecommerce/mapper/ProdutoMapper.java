package br.com.dominio.projetoecommerce.mapper;

import br.com.dominio.projetoecommerce.exception.MapToDtoException;
import br.com.dominio.projetoecommerce.exception.MapToModelException;
import br.com.dominio.projetoecommerce.model.Categoria;
import br.com.dominio.projetoecommerce.model.Produto;
import br.com.dominio.projetoecommerce.model.dto.CategoriaDto;
import br.com.dominio.projetoecommerce.model.dto.ProdutoDto;

import java.util.stream.Collectors;

public class ProdutoMapper {
  public static Produto toModel(ProdutoDto dto) {
    if (dto == null) {
      throw new MapToModelException();
    }

    Produto model = new Produto();
    model.setId(dto.getId());
    model.setNome(dto.getNome());
    model.setPreco(dto.getPreco());
    dto.setCategorias(model.getCategorias().stream().map(CategoriaMapper::toDto).collect(Collectors.toList()));
    return model;
  }

  public static ProdutoDto toDto(Produto model) {
    if (model == null) {
      throw new MapToDtoException();
    }

    ProdutoDto dto = new ProdutoDto();
    dto.setId(model.getId());
    dto.setNome(model.getNome());
    dto.setPreco(model.getPreco());
    dto.setCategorias(model.getCategorias().stream().map(CategoriaMapper::toDto).collect(Collectors.toList()));
    return dto;
  }

}
