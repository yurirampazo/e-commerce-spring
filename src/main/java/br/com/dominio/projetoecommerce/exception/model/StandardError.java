package br.com.dominio.projetoecommerce.exception.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StandardError implements Serializable {
  private Integer status;
  private String message;
  @JsonFormat(pattern = "dd-MM-yyyy, HH:mm")
  private LocalDateTime instant;
  private String uri;
}
