package br.com.dominio.projetoecommerce.model.dto;

import br.com.dominio.projetoecommerce.exception.MapToModelException;
import br.com.dominio.projetoecommerce.model.Categoria;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class CategoriaDto implements Serializable {
  private Integer id;
  private String nome;

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
