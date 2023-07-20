package br.com.dominio.projetoecommerce.domain.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@JsonInclude
@Getter
@Setter
public class ClienteDto implements Serializable {

  private String nome;
  private String email;
  private Set<String> telefones;
}
