package br.com.dominio.projetoecommerce.enums;

import br.com.dominio.projetoecommerce.exception.MapEnumException;

public enum EstadoPagamento {
  PENDENTE (1, "Pendente"),
  QUITADO (2, "Quitado"),
  CANCELADO (3, "Cancelado");

  private Integer estado;
  private String descricao;

  EstadoPagamento(Integer estado, String descricao) {
    this.estado = estado;
    this.descricao = descricao;
  }

  public String getDescricao() {
    return descricao;
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
