package br.viesant.labmedical_x2.services;

import static br.viesant.labmedical_x2.mappers.PacienteMapper.toEntity;

import br.viesant.labmedical_x2.controllers.DTO.PacienteRequest;
import br.viesant.labmedical_x2.entities.PacienteEntity;
import br.viesant.labmedical_x2.entities.UsuarioEntity;
import br.viesant.labmedical_x2.repositories.PacienteRepository;
import br.viesant.labmedical_x2.repositories.UsuarioRepository;
import com.sun.jdi.request.DuplicateRequestException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

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

  public PacienteEntity findById(Long id, JwtAuthenticationToken token) {

    // valida se usuario recebido no token existe
    UsuarioEntity usuario =
        usuarioRepository
            .findByEmail(token.getName())
            .orElseThrow(() -> new EntityNotFoundException("Usuario do token não encontrado"));

    if (usuario.isPaciente()) {
      PacienteEntity paciente =
          pacienteRepository
              .findByUsuario(usuario)
              .orElseThrow(
                  () ->
                      new EntityNotFoundException(
                          "Usuário não associado a um cadastro de Paciente"));

      if (!paciente.getId().equals(id)) {
        throw new AccessDeniedException("Usuário não pode acessar informações de outro paciente");
      }
    }

    return pacienteRepository
        .findById(id)
        .orElseThrow(
            () -> new EntityNotFoundException("Nenhum paciente encontrado " + "com id: " + id));
  }
}
