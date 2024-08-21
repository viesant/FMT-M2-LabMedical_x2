package br.viesant.labmedical_x2.controllers;

import br.viesant.labmedical_x2.controllers.DTO.LoginRequest;
import br.viesant.labmedical_x2.controllers.DTO.TokenResponse;
import br.viesant.labmedical_x2.services.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Validated
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  public TokenResponse login(@Valid @RequestBody LoginRequest loginRequest) {

    return authService.authenticate(loginRequest);
  }
}
