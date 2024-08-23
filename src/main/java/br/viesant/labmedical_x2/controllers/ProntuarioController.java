package br.viesant.labmedical_x2.controllers;

import br.viesant.labmedical_x2.controllers.DTO.ProntuarioPacienteResponse;
import br.viesant.labmedical_x2.controllers.DTO.ProntuarioResponse;
import br.viesant.labmedical_x2.services.ProntuarioService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Validated
public class ProntuarioController {

  private final ProntuarioService prontuarioService;

  @GetMapping("/pacientes/prontuarios")
  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_MEDICO')")
  @ResponseStatus(HttpStatus.OK)
  public Page<ProntuarioResponse> readAllProntuarioPageable(
      @RequestParam(required = false) Long id,
      @RequestParam(required = false, defaultValue = "") String nome,
      Pageable pageable) {
    return prontuarioService.findAll(id, nome, pageable);
  }

  @GetMapping("/pacientes/{id}/prontuarios")
  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_MEDICO')")
  @ResponseStatus(HttpStatus.OK)
  public ProntuarioPacienteResponse readProntuarioByPacienteId(@PathVariable Long id) {
    return prontuarioService.findByPacienteId(id);
  }
}
