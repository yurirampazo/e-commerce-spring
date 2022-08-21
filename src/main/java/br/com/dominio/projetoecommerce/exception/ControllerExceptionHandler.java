package br.com.dominio.projetoecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(IdNotFoundException.class)
  public ResponseEntity<StandardError> objectNotFound(IdNotFoundException e) {
    StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), LocalDateTime.now());
    return ResponseEntity.status(err.getStatus()).body(err);
  }

  @ExceptionHandler(PostNotAllowedException.class)
  public ResponseEntity<StandardError> postNotAllowed(PostNotAllowedException e) {
    StandardError err = new StandardError(HttpStatus.FORBIDDEN.value(), e.getCause().getMessage(), LocalDateTime.now());
    return ResponseEntity.status(err.getStatus()).body(err);
  }

  @ExceptionHandler(DocumentNumberAlreadyExistsException.class)
  public ResponseEntity<StandardError> documentNotAllowed(DocumentNumberAlreadyExistsException e) {
    StandardError err = new StandardError(HttpStatus.FORBIDDEN.value(), e.getMessage(), LocalDateTime.now());
    return ResponseEntity.status(err.getStatus()).body(err);
  }

}
