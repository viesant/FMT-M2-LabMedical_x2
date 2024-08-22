package br.viesant.labmedical_x2.services;

import static br.viesant.labmedical_x2.mappers.ConsultaMapper.toResponse;
import static br.viesant.labmedical_x2.mappers.ConsultaMapper.updateEntity;

import br.viesant.labmedical_x2.controllers.DTO.ConsultaRequest;
import br.viesant.labmedical_x2.controllers.DTO.ConsultaResponse;
import br.viesant.labmedical_x2.entities.ConsultaEntity;
import br.viesant.labmedical_x2.entities.PacienteEntity;
import br.viesant.labmedical_x2.entities.UsuarioEntity;
import br.viesant.labmedical_x2.mappers.ConsultaMapper;
import br.viesant.labmedical_x2.repositories.ConsultaRepository;
import br.viesant.labmedical_x2.repositories.PacienteRepository;
import br.viesant.labmedical_x2.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConsultaService {
  private final ConsultaRepository consultaRepository;
  private final PacienteRepository pacienteRepository;
  private final UsuarioRepository usuarioRepository;

  public ConsultaResponse create(ConsultaRequest consultaRequest) {
    // Valida pacienteId:
    PacienteEntity paciente = validateRequestId(consultaRequest.pacienteId());

    ConsultaEntity consulta = ConsultaMapper.toEntity(consultaRequest, paciente);

    return toResponse(consultaRepository.save(consulta));
  }

  public ConsultaResponse findById(Long id, JwtAuthenticationToken token) {

    // valida se usuario recebido no token existe
    UsuarioEntity usuario =
        usuarioRepository
            .findByEmail(token.getName())
            .orElseThrow(() -> new IllegalArgumentException("Usuario do token não encontrado"));

    if (usuario.isPaciente()) {
      PacienteEntity paciente =
          pacienteRepository
              .findByUsuario(usuario)
              .orElseThrow(
                  () ->
                      new IllegalArgumentException(
                          "Usuário não associado a um cadastro de Paciente"));
      return consultaRepository
          .findByIdAndPacienteId(id, paciente.getId())
          .orElseThrow(
              () ->
                  new EntityNotFoundException(
                      "Nenhuma consulta encontrada com id: "
                          + id
                          + "\npara o paciente com id: "
                          + paciente.getId()));
    }

    return toResponse(
        consultaRepository
            .findById(id)
            .orElseThrow(
                () -> new EntityNotFoundException("Nenhuma consulta encontrada com id: " + id)));
  }

  public ConsultaResponse update(Long id, ConsultaRequest consultaRequest) {
    ConsultaEntity consulta =
        consultaRepository
            .findById(id)
            .orElseThrow(
                () -> new EntityNotFoundException("Nenhuma consulta encontrado com id: " + id));

    // verifica se request.pacienteId é diferente de consulta.pacienteId:
    if (!consultaRequest.pacienteId().equals(consulta.getPaciente().getId())) {

      // Valida pacienteId:
      PacienteEntity novoPaciente = validateRequestId(consultaRequest.pacienteId());

      consulta.setPaciente(novoPaciente);
    }

    // mapeia pacienteRequest para pacienteEntity:
    updateEntity(consultaRequest, consulta);

    return toResponse(consultaRepository.save(consulta));
  }

  public void delete(Long id) {
    if (!consultaRepository.existsById(id)) { // não faz sentido, mas blz
      throw new EntityNotFoundException("Nenhuma consulta encontrado com id: " + id);
    }
    consultaRepository.deleteById(id);
  }

  public List<ConsultaResponse> findAll() {
    return consultaRepository.findAll().stream()
        .map(ConsultaMapper::toResponse)
        .collect(Collectors.toList());
  }

  public List<ConsultaResponse> findAllByPacienteId(Long pacienteId) {
    return consultaRepository.findAllByPacienteId(pacienteId);
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
}
