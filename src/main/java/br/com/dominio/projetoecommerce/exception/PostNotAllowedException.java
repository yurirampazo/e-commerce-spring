package br.com.dominio.projetoecommerce.exception;

public class PostNotAllowedException extends RuntimeException {

  public PostNotAllowedException(String message) {
    super("Objeto com mesmo nome já exite!");
  }
}
