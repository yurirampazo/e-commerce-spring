package br.com.dominio.projetoecommerce.exception;

public class MapToModelException extends RuntimeException {
  public MapToModelException() {
    super("Não foi possível mapear o DTO para entidade!");
  }

  public MapToModelException(String msg) {
    super(msg);
  }
}
