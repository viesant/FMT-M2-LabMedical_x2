package br.viesant.labmedical_x2.services;

import br.viesant.labmedical_x2.controllers.DTO.DashboardResponse;
import br.viesant.labmedical_x2.repositories.ConsultaRepository;
import br.viesant.labmedical_x2.repositories.ExameRepository;
import br.viesant.labmedical_x2.repositories.PacienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DashboardService {

  private final PacienteRepository pacienteRepository;
  private final ConsultaRepository consultaRepository;
  private final ExameRepository exameRepository;

  public DashboardResponse getData() {
    return new DashboardResponse(
        pacienteRepository.count(), consultaRepository.count(), exameRepository.count());
  }
}
