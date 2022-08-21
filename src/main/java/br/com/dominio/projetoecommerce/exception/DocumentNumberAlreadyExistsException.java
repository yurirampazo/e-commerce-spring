package br.com.dominio.projetoecommerce.exception;

public class DocumentNumberAlreadyExistsException extends PostNotAllowedException {
  public DocumentNumberAlreadyExistsException(String message) {
    super("O número de documento já existe!");
  }
}
