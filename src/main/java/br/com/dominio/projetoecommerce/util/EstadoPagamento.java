package br.com.dominio.projetoecommerce.util;

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

}
