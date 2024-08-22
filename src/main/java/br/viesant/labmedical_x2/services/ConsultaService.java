package br.viesant.labmedical_x2.services;

import br.viesant.labmedical_x2.controllers.DTO.ConsultaRequest;
import br.viesant.labmedical_x2.controllers.DTO.ConsultaResponse;
import br.viesant.labmedical_x2.controllers.DTO.PacienteResponse;
import br.viesant.labmedical_x2.entities.ConsultaEntity;
import br.viesant.labmedical_x2.entities.PacienteEntity;
import br.viesant.labmedical_x2.mappers.ConsultaMapper;
import br.viesant.labmedical_x2.repositories.ConsultaRepository;
import br.viesant.labmedical_x2.repositories.PacienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ConsultaService {
  private final ConsultaRepository consultaRepository;
  private final PacienteRepository pacienteRepository;

  public ConsultaEntity create(ConsultaRequest consultaRequest) {
    // Valida pacienteId:
    PacienteEntity paciente = validateRequestId(consultaRequest.pacienteId());

    ConsultaEntity consulta = ConsultaMapper.toEntity(consultaRequest, paciente);

    return consultaRepository.save(consulta);
  }

  private PacienteEntity validateRequestId(Long pacienteId) {
    // valida se pacienteId corresponde a um paciente:
    PacienteEntity paciente =
        pacienteRepository
            .findById(pacienteId)
            .orElseThrow(
                () -> new IllegalArgumentException("Não existe Paciente com id: " + pacienteId));
    return paciente;
  }

  public List<ConsultaResponse> findAll() {
    return consultaRepository.findAll().stream()
                   .map(ConsultaMapper::toResponse)
                   .collect(Collectors.toList());
  }

  public List<ConsultaResponse> findAllByPacienteId(Long pacienteId) {
    return consultaRepository.findAllByPacienteId(pacienteId);
  }
}
