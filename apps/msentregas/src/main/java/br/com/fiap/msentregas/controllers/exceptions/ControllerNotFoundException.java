package br.com.fiap.msentregas.controllers.exceptions;

/**
 * Classe de ControllerNotFoundException.
 **/
public class ControllerNotFoundException extends RuntimeException {
  public ControllerNotFoundException(String message) {
    super(message);
  }
}
