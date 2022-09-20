package br.com.dominio.projetoecommerce.mapper;

import br.com.dominio.projetoecommerce.exception.MapToModelException;
import br.com.dominio.projetoecommerce.model.Estado;
import br.com.dominio.projetoecommerce.model.dto.EstadoDto;

public class EstadoMapper {
  public static Estado toModel(EstadoDto dto) {
    if (dto == null) {
      throw new MapToModelException();
    }

    Estado model = new Estado();
    model.setId(dto.getId());
    model.setNome(dto.getNome());
    return model;
  }

  public static EstadoDto toDto(Estado model) {
    if (model == null) {
      throw new MapToModelException();
    }

    EstadoDto dto = new EstadoDto();
    dto.setId(model.getId());
    dto.setNome(model.getNome());
    return dto;
  }
}
