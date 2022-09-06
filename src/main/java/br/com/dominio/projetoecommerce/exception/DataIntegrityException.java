package br.com.dominio.projetoecommerce.exception;

public class DataIntegrityException extends RuntimeException {
  public DataIntegrityException(String message) {
    super(message);
  }

  public DataIntegrityException() {
    super("Não é possível excluir um elemento com associação!");
  }
}
