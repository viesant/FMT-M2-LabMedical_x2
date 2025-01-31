package br.viesant.labmedical_x2.controllers;

import br.viesant.labmedical_x2.controllers.DTO.UsuarioReponse;
import br.viesant.labmedical_x2.controllers.DTO.UsuarioRequest;
import br.viesant.labmedical_x2.entities.UsuarioEntity;
import br.viesant.labmedical_x2.services.UsuarioService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/usuarios")
public class UsuarioController {

  private final UsuarioService usuarioService;

  @PostMapping
  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
  @ResponseStatus(HttpStatus.CREATED)
  public UsuarioReponse createUser(@Valid @RequestBody UsuarioRequest usuarioRequest) {
    return usuarioService.create(usuarioRequest);
  }

  // somente para testes:
  @GetMapping
  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
  @ResponseStatus(HttpStatus.OK)
  public List<UsuarioReponse> readAllUsers() {
    return usuarioService.findAll();
  }
}
