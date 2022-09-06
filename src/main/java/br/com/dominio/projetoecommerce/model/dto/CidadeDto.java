package br.com.dominio.projetoecommerce.model.dto;

import br.com.dominio.projetoecommerce.exception.MapToModelException;
import br.com.dominio.projetoecommerce.model.Cidade;
import br.com.dominio.projetoecommerce.model.Estado;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@NoArgsConstructor
@Getter
@Setter
public class CidadeDto implements Serializable {

  private Integer id;
  private String nome;
  private Estado estado;

  public Cidade toModel(CidadeDto dto) {
    if (dto == null) {
      throw new MapToModelException();
    }

    Cidade model = new Cidade();
    model.setId(dto.getId());
    model.setNome(dto.getNome());
    model.setEstado(dto.getEstado());
    return model;
  }
}
