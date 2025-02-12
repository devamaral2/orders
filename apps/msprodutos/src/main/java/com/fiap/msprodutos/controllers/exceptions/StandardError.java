package com.fiap.msprodutos.controllers.exceptions;

import java.time.Instant;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe base para as classes de error.
 **/
@Getter
@Setter
@Builder
public class StandardError {
  private Instant timestamp;
  private Integer status;
  private String error;
  private String message;
  private String path;

}
