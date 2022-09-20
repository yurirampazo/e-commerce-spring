package br.com.dominio.projetoecommerce.mapper;

import br.com.dominio.projetoecommerce.exception.MapToDtoException;
import br.com.dominio.projetoecommerce.exception.MapToModelException;
import br.com.dominio.projetoecommerce.model.Cidade;
import br.com.dominio.projetoecommerce.model.Estado;
import br.com.dominio.projetoecommerce.model.dto.CidadeDto;

public class CidadeMapper {
  public static Cidade toModel(CidadeDto dto) {
    if (dto == null) {
      throw new MapToModelException();
    }

    Cidade model = new Cidade();
    model.setId(dto.getId());
    model.setNome(dto.getNome());
    model.setEstado(EstadoMapper.toModel(dto.getEstado()));
    return model;
  }

  public static CidadeDto toDto(Cidade model) {
    if (model == null) {
      throw new MapToDtoException();
    }

    CidadeDto dto = new CidadeDto();
    dto.setId(model.getId());
    dto.setNome(model.getNome());
    dto.setEstado(EstadoMapper.toDto(model.getEstado()));
    return dto;
  }
}
