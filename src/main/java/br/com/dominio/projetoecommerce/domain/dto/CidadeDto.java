package br.com.dominio.projetoecommerce.domain.dto;

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
  private EstadoDto estado;
}
