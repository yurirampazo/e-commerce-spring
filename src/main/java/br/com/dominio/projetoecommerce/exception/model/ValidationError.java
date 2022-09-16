package br.com.dominio.projetoecommerce.exception.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class ValidationError extends StandardError implements Serializable {

  private List<FieldMessage> list = new ArrayList<>();

  public ValidationError(Integer status, String message, LocalDateTime instant, String uri) {
    super(status, message, instant, uri);
  }

  public void addError(String nome, String message) {
    list.add(new FieldMessage(nome, message));
  }
}
