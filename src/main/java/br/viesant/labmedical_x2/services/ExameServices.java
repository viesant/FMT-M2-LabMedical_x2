package br.viesant.labmedical_x2.services;

import static br.viesant.labmedical_x2.mappers.ExameMapper.toResponse;
import static br.viesant.labmedical_x2.mappers.ExameMapper.updateEntity;

import br.viesant.labmedical_x2.controllers.DTO.ExameRequest;
import br.viesant.labmedical_x2.controllers.DTO.ExameResponse;
import br.viesant.labmedical_x2.entities.ExameEntity;
import br.viesant.labmedical_x2.entities.PacienteEntity;
import br.viesant.labmedical_x2.entities.UsuarioEntity;
import br.viesant.labmedical_x2.mappers.ExameMapper;
import br.viesant.labmedical_x2.repositories.ExameRepository;
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
public class ExameServices {
  private final ExameRepository exameRepository;
  private final PacienteRepository pacienteRepository;
  private final UsuarioRepository usuarioRepository;

  public ExameResponse create(ExameRequest exameRequest) {
    // Valida pacienteId:
    PacienteEntity paciente = validateRequestId(exameRequest.pacienteId());

    ExameEntity exame = ExameMapper.toEntity(exameRequest, paciente);

    return toResponse(exameRepository.save(exame));
  }

  public ExameResponse findById(Long id, JwtAuthenticationToken token) {

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
      return exameRepository
          .findByIdAndPacienteId(id, paciente.getId())
          .orElseThrow(
              () ->
                  new EntityNotFoundException(
                      "Nenhum exame encontrada com id: "
                          + id
                          + "\npara o paciente com id: "
                          + paciente.getId()));
    }

    return toResponse(
        exameRepository
            .findById(id)
            .orElseThrow(
                () -> new EntityNotFoundException("Nenhum exame encontrada com id: " + id)));
  }

  public ExameResponse update(Long id, ExameRequest exameRequest) {
    ExameEntity exame =
        exameRepository
            .findById(id)
            .orElseThrow(
                () -> new EntityNotFoundException("Nenhum exame encontrado com id: " + id));

    // verifica se request.pacienteId é diferente de exame.pacienteId:
    if (!exameRequest.pacienteId().equals(exame.getPaciente().getId())) {

      // Valida pacienteId:
      PacienteEntity novoPaciente = validateRequestId(exameRequest.pacienteId());

      exame.setPaciente(novoPaciente);
    }

    // mapeia pacienteRequest para pacienteEntity:
    updateEntity(exameRequest, exame);

    return toResponse(exameRepository.save(exame));
  }

  public void delete(Long id) {
    if (!exameRepository.existsById(id)) { // não faz sentido, mas blz
      throw new EntityNotFoundException("Nenhum exame encontrado com id: " + id);
    }
    exameRepository.deleteById(id);
  }

  public List<ExameResponse> findAll() {
    return exameRepository.findAll().stream()
        .map(ExameMapper::toResponse)
        .collect(Collectors.toList());
  }

  public List<ExameResponse> findAllByPacienteId(Long pacienteId) {
    return exameRepository.findAllByPacienteId(pacienteId);
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
