package br.com.dominio.projetoecommerce.exception;

public class MapToDtoException extends RuntimeException {
  public MapToDtoException() {
    super("Não foi possível mapear a entidade para DTO: Cliente pode não existir na base de dados");
  }
}
