package br.com.dominio.projetoecommerce.model.dto;


import br.com.dominio.projetoecommerce.model.Endereco;
import br.com.dominio.projetoecommerce.service.validation.ClienteUpdate;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@JsonInclude
@Getter
@Setter
@ClienteUpdate
public class ClienteDto implements Serializable {

  private String nome;
  private String email;
  private Endereco endereco;
  private String telefone;
}
