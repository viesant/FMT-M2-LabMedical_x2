package br.viesant.labmedical_x2.controllers;

import br.viesant.labmedical_x2.controllers.DTO.DashboardResponse;
import br.viesant.labmedical_x2.controllers.DTO.ProntuarioResponse;
import br.viesant.labmedical_x2.services.DashboardService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Validated
public class DashboardController {
  private final DashboardService dashboardService;


  @GetMapping("/dashboard")
  @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN', 'SCOPE_MEDICO')")
  @ResponseStatus(HttpStatus.OK)
  public DashboardResponse readDashboard() {
    return dashboardService.getData();
  }

}
