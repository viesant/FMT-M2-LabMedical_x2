package br.viesant.labmedical_x2.services;

import br.viesant.labmedical_x2.controllers.DTO.PacienteRequest;
import br.viesant.labmedical_x2.entities.PacienteEntity;
import br.viesant.labmedical_x2.entities.UsuarioEntity;
import br.viesant.labmedical_x2.repositories.PacienteRepository;
import br.viesant.labmedical_x2.repositories.UsuarioRepository;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static br.viesant.labmedical_x2.mappers.PacienteMapper.toEntity;

@Service
@AllArgsConstructor
public class PacienteService {
  private final UsuarioRepository usuarioRepository;
  private final PacienteRepository pacienteRepository;

  public PacienteEntity create(PacienteRequest pacienteRequest) {
    // verifica se existe usuario com id do request:
    UsuarioEntity usuario =
        usuarioRepository
            .findById(pacienteRequest.usuarioId())
            .orElseThrow(
                () ->
                    // new EntityNotFoundException(
                    new IllegalArgumentException(
                        "Não existe usuario com id: " + pacienteRequest.usuarioId()));
    // e se usuario é paciente:
    if (!usuario.isPaciente()) {
      throw new IllegalArgumentException(
          "Usuário com id " + pacienteRequest.usuarioId() + " não tem perfil de 'PACIENTE'");
    }

    // if (pacienteRepository.existsByUsuario(usuario)) {
    if (pacienteRepository.existsByUsuario_Id(pacienteRequest.usuarioId())) {
      throw new DuplicateRequestException("Já existe um paciente cadastrado com este usuário.");
    }

    // mapeia request to entity:
    PacienteEntity paciente = toEntity(pacienteRequest, usuario);

    return pacienteRepository.save(paciente);

  }
}
