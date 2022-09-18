package br.com.dominio.projetoecommerce.exception;

public class IdNotFoundException extends RuntimeException {
  public IdNotFoundException() {
    super("Id não encontrado!");
  }

  public IdNotFoundException(Integer id) {
    super("Id: " + id + " não encontrado!");
  }
  public IdNotFoundException(String message) {
    super(message);
  }
}
