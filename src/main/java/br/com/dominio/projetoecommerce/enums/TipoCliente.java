package br.com.dominio.projetoecommerce.enums;

import br.com.dominio.projetoecommerce.exception.MapEnumException;

public enum TipoCliente {
  PESSOAFISICA(1, "Pessoa Física"),
  PESSOAJURIDICA(2, "Pessoa Jurídica");

  private Integer tipo;
  private String descricao;

  TipoCliente(Integer tipo, String descricao) {
    this.tipo = tipo;
    this.descricao = descricao;
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
