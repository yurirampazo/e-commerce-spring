package br.com.dominio.projetoecommerce.enums;

import br.com.dominio.projetoecommerce.exception.MapEnumException;

public enum Perfil {
  ADMIN(1, "ROLE_ADMIN"),
  CLIENTE(2, "ROLE_CLIENTE");

  private Integer tipo;
  private String descricao;

  Perfil(Integer tipo, String descricao) {
    this.tipo = tipo;
    this.descricao = descricao;
  }

  public Integer getTipo() {
    return tipo;
  }

  public String getDescricao() {
    return descricao;
  }

  public static Perfil toEnum(Integer cod) {
    if (cod == null) {
      throw new MapEnumException();
    }

    for (Perfil x : Perfil.values()) {
      if (x.getTipo().equals(cod)) {
        return x;
      }
    }
    throw new IllegalArgumentException("Id inválido: " + cod);
  }
}
