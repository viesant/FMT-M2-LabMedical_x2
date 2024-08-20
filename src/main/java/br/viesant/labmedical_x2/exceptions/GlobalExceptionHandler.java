package br.viesant.labmedical_x2.exceptions;

import br.viesant.labmedical_x2.exceptions.DTO.ErrorValidationResponse;
import java.util.List;

import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  // autenticação username e password:
  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<String> handleAuthenticationException(AuthenticationException ex) {
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email e/ou senha inválidos");
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<String> handleUserNotFound(UsernameNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getLocalizedMessage());
  }

  // Validação de formulários:
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<List<ErrorValidationResponse>> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex) {

    List<ErrorValidationResponse> erros =
        ex.getFieldErrors().stream()
            .map(erro -> new ErrorValidationResponse(erro.getField(), erro.getDefaultMessage()))
            .toList();

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros);
  }
  // Erro de leitura de mensagem JSON:
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<String> handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                   .body("Erro de leitura de mensagem JSON: " + e.getMessage());
  }
  // Erro de análise JSON:
  @ExceptionHandler(JsonParseException.class)
  public ResponseEntity<String> handleJsonParseException(JsonParseException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                   .body("Erro de análise JSON: " + e.getMessage());
  }

  // Genérico:
  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGenericException(Exception e) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body("Ocorreu um erro inesperado: " + e.getMessage());
  }
}
