package br.viesant.labmedical_x2.controllers;

import br.viesant.labmedical_x2.controllers.DTO.PacienteRequest;
import br.viesant.labmedical_x2.controllers.DTO.UsuarioRequest;
import br.viesant.labmedical_x2.entities.PacienteEntity;
import br.viesant.labmedical_x2.entities.UsuarioEntity;
import br.viesant.labmedical_x2.services.PacienteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/pacientes")
public class PacienteController {
  private final PacienteService pacienteService;

  @PostMapping
  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
  @ResponseStatus(HttpStatus.CREATED)
  public PacienteEntity createUser(@Valid @RequestBody PacienteRequest pacienteRequest) {
    return pacienteService.create(pacienteRequest);
  }
/*
  @GetMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
  @ResponseStatus(HttpStatus.OK)
  public  getPacienteById(@PathVariable Long id) {
    return pacienteService.findById(id);
  }
  @GetMapping("/{id}")
  public ResponseEntity<PacienteEntity> buscaPacientePorId(@PathVariable Long id, JwtAuthenticationToken token) {
    return ResponseEntity.ok(pacienteService.buscaPacientePorId(id, token));
  }
*/


  // Get{Id}
  // GetAll (pageable+filter)

  // Put{id}

  //Delete{id}



}
