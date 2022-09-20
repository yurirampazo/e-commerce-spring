package br.com.dominio.projetoecommerce.model.dto;

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
}
