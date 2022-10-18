package br.com.dominio.projetoecommerce.exception.handler;

import br.com.dominio.projetoecommerce.exception.DataIntegrityException;
import br.com.dominio.projetoecommerce.exception.DocumentNotFoundException;
import br.com.dominio.projetoecommerce.exception.DocumentNumberAlreadyExistsException;
import br.com.dominio.projetoecommerce.exception.IdNotFoundException;
import br.com.dominio.projetoecommerce.exception.MapEnumException;
import br.com.dominio.projetoecommerce.exception.MapToDtoException;
import br.com.dominio.projetoecommerce.exception.MapToModelException;
import br.com.dominio.projetoecommerce.exception.PageNotFoundException;
import br.com.dominio.projetoecommerce.exception.PostNotAllowedException;
import br.com.dominio.projetoecommerce.exception.model.StandardError;
import br.com.dominio.projetoecommerce.exception.model.ValidationError;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(IdNotFoundException.class)
  public ResponseEntity<StandardError> idtNotFound(IdNotFoundException e, HttpServletRequest request) {
    StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(),
          LocalDateTime.now(), request.getRequestURI());
    return ResponseEntity.status(err.getStatus()).body(err);
  }

  @ExceptionHandler(DocumentNotFoundException.class)
  public ResponseEntity<StandardError> idtNotFound(DocumentNotFoundException e, HttpServletRequest request) {
    StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(),
          LocalDateTime.now(), request.getRequestURI());
    return ResponseEntity.status(err.getStatus()).body(err);
  }
  @ExceptionHandler(PostNotAllowedException.class)
  public ResponseEntity<StandardError> postNotAllowed(PostNotAllowedException e, HttpServletRequest request) {
    StandardError err = new StandardError(HttpStatus.FORBIDDEN.value(), e.getMessage(),
          LocalDateTime.now(),
          request.getRequestURI());
    return ResponseEntity.status(err.getStatus()).body(err);
  }

  @ExceptionHandler(DocumentNumberAlreadyExistsException.class)
  public ResponseEntity<StandardError> documentNotAllowed(DocumentNumberAlreadyExistsException e, HttpServletRequest request) {
    StandardError err = new StandardError(HttpStatus.FORBIDDEN.value(), e.getMessage(),
          LocalDateTime.now(), request.getRequestURI());
    return ResponseEntity.status(err.getStatus()).body(err);
  }

  @ExceptionHandler(DataIntegrityException.class)
  public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
    StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),
          LocalDateTime.now(), request.getRequestURI());
    return ResponseEntity.status(err.getStatus()).body(err);
  }

//  @ExceptionHandler(MapToDtoException.class)
//  public ResponseEntity<StandardError> dataIntegrity(MapToDtoException e, HttpServletRequest request) {
//    StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),
//          LocalDateTime.now(), request.getRequestURI());
//    return ResponseEntity.status(err.getStatus()).body(err);
//  }

  @ExceptionHandler(MapToModelException.class)
  public ResponseEntity<StandardError> dataIntegrity(MapToModelException e, HttpServletRequest request) {
    StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),
          LocalDateTime.now(), request.getRequestURI());
    return ResponseEntity.status(err.getStatus()).body(err);
  }

  @ExceptionHandler(PageNotFoundException.class)
  public ResponseEntity<StandardError> dataIntegrity(PageNotFoundException e, HttpServletRequest request) {
    StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(),
          LocalDateTime.now(), request.getRequestURI());
    return ResponseEntity.status(err.getStatus()).body(err);
  }

    @ExceptionHandler(MapEnumException.class)
    public ResponseEntity<StandardError> dataIntegrity(MapEnumException e, HttpServletRequest request) {
      StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),
            LocalDateTime.now(), request.getRequestURI());
      return ResponseEntity.status(err.getStatus()).body(err);
    }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
    ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação: ",
          LocalDateTime.now(), request.getRequestURI());

//    for (FieldError x : e.getBindingResult().getFieldErrors()) {
//      err.addError(x.getField(), x.getDefaultMessage());
//    }

    e.getBindingResult().getFieldErrors().forEach(x -> err.addError(x.getField(), x.getDefaultMessage()));

    return ResponseEntity.status(err.getStatus()).body(err);

  }

//  @ExceptionHandler(ConstraintViolationException.class)
//  public ResponseEntity<StandardError> validation(ConstraintViolationException e, HttpServletRequest request) {
//    StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), "Erro de validação: " +
//          e.getCause().getMessage(),
//          LocalDateTime.now(), request.getRequestURI());
//
//    return ResponseEntity.status(err.getStatus()).body(err);
//  }

  @ExceptionHandler(IllegalStateException.class)
  public ResponseEntity<StandardError> validation(IllegalStateException e, HttpServletRequest request) {
    StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), "Erro de validação: " +
          e.getCause().getMessage(),
          LocalDateTime.now(), request.getRequestURI());

    return ResponseEntity.status(err.getStatus()).body(err);
  }

  @ExceptionHandler(InvalidDataAccessApiUsageException.class)
  public ResponseEntity<StandardError> validation(InvalidDataAccessApiUsageException e, HttpServletRequest request) {
    StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), "Erro de validação: " +
          e.getCause().getMessage(),
          LocalDateTime.now(), request.getRequestURI());

    return ResponseEntity.status(err.getStatus()).body(err);
  }
}
