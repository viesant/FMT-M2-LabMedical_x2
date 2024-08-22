package br.viesant.labmedical_x2.services;

import br.viesant.labmedical_x2.controllers.DTO.LoginRequest;
import br.viesant.labmedical_x2.controllers.DTO.TokenResponse;
import java.time.Instant;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
  private final AuthenticationManager authenticationManager;
  private final JwtEncoder jwtEncoder;

  public TokenResponse authenticate(LoginRequest loginRequest) {
    // validação de login:
    UsernamePasswordAuthenticationToken authToken =
        new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());

    Authentication authentication = authenticationManager.authenticate(authToken);

    // geração de token:
    long expiration = 36000L;
    Instant now = Instant.now();

    String perfis =
        authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(" "));

    JwtClaimsSet claims =
        JwtClaimsSet.builder()
            .issuer("viesant.LabMedical")
            .subject(authentication.getName())
            .issuedAt(now)
            .expiresAt(now.plusSeconds(expiration))
            .claim("scope", perfis)
            .build();
    String token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

    return new TokenResponse(token, expiration);
  }
}
