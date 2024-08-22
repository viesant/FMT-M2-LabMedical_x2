package br.viesant.labmedical_x2.controllers;

import br.viesant.labmedical_x2.controllers.DTO.ConsultaRequest;
import br.viesant.labmedical_x2.controllers.DTO.ConsultaResponse;
import br.viesant.labmedical_x2.entities.ConsultaEntity;
import br.viesant.labmedical_x2.services.ConsultaService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/consultas")
public class ConsultaController {

  private final ConsultaService consultaService;

  @PostMapping
  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
  @ResponseStatus(HttpStatus.CREATED)
  public ConsultaEntity createConsulta(@Valid @RequestBody ConsultaRequest consultaRequest) {

    return consultaService.create(consultaRequest);
  }

  // somente para testes:
  @GetMapping("/list")
  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_MEDICO')")
  @ResponseStatus(HttpStatus.OK)
  public List<ConsultaResponse> readAllConsultas() {

    return consultaService.findAll();
  }
  // somente para testes:
  @GetMapping("/list/{pacienteId}")
  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_MEDICO')")
  @ResponseStatus(HttpStatus.OK)
  public List<ConsultaResponse> readAllConsultasByPacienteId(@PathVariable Long pacienteId) {

    return consultaService.findAllByPacienteId(pacienteId);
  }
}
