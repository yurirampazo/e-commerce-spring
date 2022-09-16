package br.com.dominio.projetoecommerce.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class ValidationError extends StandardError implements Serializable {

  private List<FieldMessage> errors = new ArrayList<>();

  public ValidationError(Integer status, String message, LocalDateTime instant, String uri) {
    super(status, message, instant, uri);
  }

  public void addError(String nome, String message) {
    errors.add(new FieldMessage(nome, message));
  }

  public List<FieldMessage> getErrors() {
    return errors;
  }
}
