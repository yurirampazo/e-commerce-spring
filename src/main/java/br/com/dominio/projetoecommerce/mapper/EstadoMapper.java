package br.com.dominio.projetoecommerce.mapper;

import br.com.dominio.projetoecommerce.model.Estado;
import br.com.dominio.projetoecommerce.model.dto.EstadoDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface  EstadoMapper {
  EstadoDto toDto(Estado estado);
  Estado toModel(EstadoDto estadoDto);
}
