package br.viesant.labmedical_x2.controllers;

import br.viesant.labmedical_x2.DTO.LoginRequest;
import br.viesant.labmedical_x2.DTO.TokenResponse;
import br.viesant.labmedical_x2.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.token.Token;
import org.springframework.security.core.token.TokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {

  private final AuthService authService;


  @PostMapping("/login")
  public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {

    return ResponseEntity.ok(authService.authenticate(loginRequest));
  }
}
