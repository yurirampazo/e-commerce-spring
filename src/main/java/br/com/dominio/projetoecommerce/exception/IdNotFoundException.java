package br.com.dominio.projetoecommerce.exception;

public class IdNotFoundException extends RuntimeException {
  public IdNotFoundException(String message) {
    super("Id não encontrado!");
  }
}
