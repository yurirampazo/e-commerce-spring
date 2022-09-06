package br.com.dominio.projetoecommerce.exception;

public class PageNotFoundException extends RuntimeException {

  public PageNotFoundException(Integer pagina) {
    super("Página " + pagina + " não encontrada!");
  }
}
