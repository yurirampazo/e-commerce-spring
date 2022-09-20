package br.com.dominio.projetoecommerce.enums;

import br.com.dominio.projetoecommerce.exception.MapEnumException;

public enum EstadoPagamento {
  PENDENTE (1),
  QUITADO (2),
  CANCELADO (3);

  private Integer estado;

  EstadoPagamento(Integer estado) {
    this.estado = estado;
  }

  public Integer getEstado () {
    return estado;
  }

  public static EstadoPagamento toEnum(Integer cod) {
    if (cod == null) {
      throw new MapEnumException();
    }

    for (EstadoPagamento x : EstadoPagamento.values()) {
      if (x.getEstado().equals(cod)) {
        return x;
      }
    }
    throw new IllegalArgumentException("Id inválido: " + cod);
  }

}
