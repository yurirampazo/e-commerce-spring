package br.com.dominio.projetoecommerce.exception;

public class MapEnumException extends RuntimeException {
  public MapEnumException() {
    super("Não foi possível mapear a opção enumerada requerida!");
  }
}
