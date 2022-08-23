package br.com.dominio.projetoecommerce.exception;

public class DocumentNumberAlreadyExistsException extends PostNotAllowedException {
  public DocumentNumberAlreadyExistsException() {
    super("Já existe um registro para esse número de documento!");
  }
}
