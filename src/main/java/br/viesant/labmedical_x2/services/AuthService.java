package br.viesant.labmedical_x2.services;

import br.viesant.labmedical_x2.DTO.LoginRequest;
import br.viesant.labmedical_x2.DTO.TokenResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
  private final AuthenticationManager authenticationManager;

  public TokenResponse authenticate(LoginRequest loginRequest) {
    UsernamePasswordAuthenticationToken authToken =
        new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());

    Authentication authentication = authenticationManager.authenticate(authToken);

    if (authentication.isAuthenticated()) {
      return new TokenResponse(
          authentication.getDetails()+ "\n-----\n" + authentication.getAuthorities()+ "\n-----\n" +
              authentication.getPrincipal()+ "\n-----\n" +authentication.getDetails()+ "\n-----\n" +authentication.getCredentials(),
          3600L);
    }
    return null;
  }
}
