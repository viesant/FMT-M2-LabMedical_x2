package br.viesant.labmedical_x2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email e/ou senha inválidos");
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGenericException(Exception e) {

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("Ocorreu um erro inesperado: " + e.getMessage());
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<String> UserNotFound(UsernameNotFoundException ex) {
    return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.NOT_FOUND);
  }
}
