package br.com.dominio.projetoecommerce.domain.dto;


import br.com.dominio.projetoecommerce.domain.Endereco;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@JsonInclude
@Getter
@Setter
public class ClienteDto implements Serializable {

  private String nome;
  private String email;
  private Endereco endereco;
  private String telefone;
  private List<String> roles = new ArrayList<>();
}
