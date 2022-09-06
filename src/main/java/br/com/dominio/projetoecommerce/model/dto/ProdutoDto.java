package br.com.dominio.projetoecommerce.model.dto;

import br.com.dominio.projetoecommerce.exception.MapToModelException;
import br.com.dominio.projetoecommerce.model.Categoria;
import br.com.dominio.projetoecommerce.model.ItemPedido;
import br.com.dominio.projetoecommerce.model.Pedido;
import br.com.dominio.projetoecommerce.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
@Setter
@NoArgsConstructor
@JsonInclude
public class ProdutoDto implements Serializable {

  private Integer id;
  private String nome;
  private BigDecimal preco;
  private List<CategoriaDto> categorias = new ArrayList<>();

  public static Produto toModel(ProdutoDto dto) {
    if (dto == null) {
      throw new MapToModelException();
    }

    Produto model = new Produto();
    model.setId(dto.getId());
    model.setNome(dto.getNome());
    model.setPreco(dto.getPreco());
    dto.setCategorias(model.getCategorias().stream().map(Categoria::toDto).collect(Collectors.toList()));
    return model;
  }


}
