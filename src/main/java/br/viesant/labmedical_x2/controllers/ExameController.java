package br.viesant.labmedical_x2.controllers;

import br.viesant.labmedical_x2.controllers.DTO.ExameRequest;
import br.viesant.labmedical_x2.controllers.DTO.ExameResponse;
import br.viesant.labmedical_x2.services.ExameServices;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/exames")
public class ExameController {
  private final ExameServices exameServices;

  @PostMapping
  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
  @ResponseStatus(HttpStatus.CREATED)
  public ExameResponse createExame(@Valid @RequestBody ExameRequest ExameRequest) {

    return exameServices.create(ExameRequest);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ExameResponse readExame(@PathVariable Long id, JwtAuthenticationToken token) {

    return exameServices.findById(id, token);
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_MEDICO')")
  @ResponseStatus(HttpStatus.OK)
  public ExameResponse updateExame(
      @PathVariable Long id, @Valid @RequestBody ExameRequest ExameRequest) {

    return exameServices.update(id, ExameRequest);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_MEDICO')")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteExame(@PathVariable Long id) {

    exameServices.delete(id);
  }

  // somente para testes:
  @GetMapping("/list")
  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_MEDICO')")
  @ResponseStatus(HttpStatus.OK)
  public List<ExameResponse> readAllExames() {

    return exameServices.findAll();
  }

  // somente para testes:
  @GetMapping("/list/{pacienteId}")
  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_MEDICO')")
  @ResponseStatus(HttpStatus.OK)
  public List<ExameResponse> readAllExamesByPacienteId(@PathVariable Long pacienteId) {

    return exameServices.findAllByPacienteId(pacienteId);
  }
}
