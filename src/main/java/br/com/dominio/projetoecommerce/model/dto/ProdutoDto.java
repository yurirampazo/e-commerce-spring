package br.com.dominio.projetoecommerce.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@JsonInclude
public class ProdutoDto implements Serializable {

  private Integer id;
  private String nome;
  private BigDecimal preco;
  private List<CategoriaDto> categorias = new ArrayList<>();

}
