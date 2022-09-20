package br.com.dominio.projetoecommerce.model.dto;


import br.com.dominio.projetoecommerce.exception.MapToModelException;
import br.com.dominio.projetoecommerce.model.Cliente;
import br.com.dominio.projetoecommerce.model.Endereco;
import br.com.dominio.projetoecommerce.service.validation.ClienteInsert;
import br.com.dominio.projetoecommerce.service.validation.ClienteUpdate;
import br.com.dominio.projetoecommerce.util.TipoCliente;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
