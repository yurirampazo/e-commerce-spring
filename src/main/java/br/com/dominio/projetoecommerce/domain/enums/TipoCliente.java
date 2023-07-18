package br.com.dominio.projetoecommerce.domain.enums;

import br.com.dominio.projetoecommerce.exception.MapEnumException;
import br.com.dominio.projetoecommerce.service.CnpjGroup;
import br.com.dominio.projetoecommerce.service.CpfGroup;

public enum TipoCliente {
  PESSOAFISICA(1, "Pessoa Física", CpfGroup.class),
  PESSOAJURIDICA(2, "Pessoa Jurídica", CnpjGroup.class);

  private final Integer tipo;
  private final String descricao;
  private final Class<?> group;


  TipoCliente(Integer tipo, String descricao, Class<?> group) {
    this.tipo = tipo;
    this.descricao = descricao;
    this.group = group;
  }

  public Class<?> getGroup() {
    return group;
  }

  public Integer getTipo() {
    return tipo;
  }

  public String getDescricao() {
    return descricao;
  }

  public static TipoCliente toEnum(Integer cod) {
    if (cod == null) {
      throw new MapEnumException();
    }

    for (TipoCliente x : TipoCliente.values()) {
      if (x.getTipo().equals(cod)) {
        return x;
      }
    }
    throw new IllegalArgumentException("Id inválido: " + cod);
  }
}
