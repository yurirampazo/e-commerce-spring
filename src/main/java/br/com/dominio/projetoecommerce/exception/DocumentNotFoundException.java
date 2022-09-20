package br.com.dominio.projetoecommerce.exception;

public class DocumentNotFoundException extends RuntimeException{
  public DocumentNotFoundException() {
    super("Documento não econtrado!");
  }
}
