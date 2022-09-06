package br.com.dominio.projetoecommerce.model.dto;

import br.com.dominio.projetoecommerce.exception.MapToModelException;
import br.com.dominio.projetoecommerce.model.Cidade;
import br.com.dominio.projetoecommerce.model.Estado;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
public class EstadoDto implements Serializable {

  private Integer id;
  private String nome;

  public static Estado toModel(EstadoDto dto) {
    if (dto == null) {
      throw new MapToModelException();
    }

    Estado model = new Estado();
    model.setId(dto.getId());
    model.setNome(dto.getNome());
    return model;
  }
}
