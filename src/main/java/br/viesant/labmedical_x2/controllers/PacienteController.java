package br.viesant.labmedical_x2.controllers;

import br.viesant.labmedical_x2.controllers.DTO.PacienteFilteredResponse;
import br.viesant.labmedical_x2.controllers.DTO.PacienteRequest;
import br.viesant.labmedical_x2.controllers.DTO.PacienteResponse;
import br.viesant.labmedical_x2.services.PacienteService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/pacientes")
public class PacienteController {

  private final PacienteService pacienteService;

  @PostMapping
  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_MEDICO')")
  @ResponseStatus(HttpStatus.CREATED)
  public PacienteResponse createPaciente(@Valid @RequestBody PacienteRequest pacienteRequest) {

    return pacienteService.create(pacienteRequest);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public PacienteResponse readPaciente(@PathVariable Long id, JwtAuthenticationToken token) {

    return pacienteService.findById(id, token);
  }

  @GetMapping
  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_MEDICO')")
  @ResponseStatus(HttpStatus.OK)
  public Page<PacienteFilteredResponse> readAllPacientesPageable(
      @RequestParam(required = false) String nome,
      @RequestParam(required = false) String telefone,
      @RequestParam(required = false) String email,
      Pageable pageable) {
    return pacienteService.findAll(nome, telefone, email, pageable);
  }

  // somente para testes:
  @GetMapping("/list")
  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_MEDICO')")
  @ResponseStatus(HttpStatus.OK)
  public List<PacienteResponse> readAllPacientes() {

    return pacienteService.findAll();
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_MEDICO')")
  @ResponseStatus(HttpStatus.OK)
  public PacienteResponse updatePaciente(
      @PathVariable Long id, @Valid @RequestBody PacienteRequest pacienteRequest) {

    return pacienteService.update(id, pacienteRequest);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_MEDICO')")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletePaciente(@PathVariable Long id) {

    pacienteService.delete(id);
  }
}
