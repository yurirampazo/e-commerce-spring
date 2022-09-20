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
}
