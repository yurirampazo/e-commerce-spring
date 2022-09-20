package br.com.dominio.projetoecommerce.exception;

public class EmailNotFoundException extends RuntimeException{
  public EmailNotFoundException() {
    super("Email não econtrado!");
  }
}
