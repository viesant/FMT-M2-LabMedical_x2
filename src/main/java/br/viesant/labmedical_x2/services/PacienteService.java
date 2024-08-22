package br.viesant.labmedical_x2.services;

import static br.viesant.labmedical_x2.mappers.PacienteMapper.toEntity;
import static br.viesant.labmedical_x2.mappers.PacienteMapper.toResponse;
import static br.viesant.labmedical_x2.mappers.PacienteMapper.updateEntity;

import br.viesant.labmedical_x2.controllers.DTO.PacienteFilteredResponse;
import br.viesant.labmedical_x2.controllers.DTO.PacienteRequest;
import br.viesant.labmedical_x2.controllers.DTO.PacienteResponse;
import br.viesant.labmedical_x2.entities.PacienteEntity;
import br.viesant.labmedical_x2.entities.UsuarioEntity;
import br.viesant.labmedical_x2.mappers.PacienteMapper;
import br.viesant.labmedical_x2.repositories.PacienteRepository;
import br.viesant.labmedical_x2.repositories.UsuarioRepository;
import com.sun.jdi.request.DuplicateRequestException;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PacienteService {

  private final UsuarioRepository usuarioRepository;
  private final PacienteRepository pacienteRepository;

  public PacienteResponse create(PacienteRequest pacienteRequest) {
    // Valida usuarioId:
    UsuarioEntity usuario = validateRequestId(pacienteRequest.usuarioId());

    // mapeia request to entity:
    PacienteEntity paciente = toEntity(pacienteRequest, usuario);

    return toResponse(pacienteRepository.save(paciente));
  }

  public PacienteResponse findById(Long id, JwtAuthenticationToken token) {

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

      if (!paciente.getId().equals(id)) {
        throw new AccessDeniedException("Usuário não pode acessar informações de outro paciente");
      }
    }

    return toResponse(
        pacienteRepository
            .findById(id)
            .orElseThrow(
                () -> new EntityNotFoundException("Nenhum paciente encontrado com id: " + id)));
  }

  public List<PacienteResponse> findAll() {
    return pacienteRepository.findAll().stream()
        .map(PacienteMapper::toResponse)
        .collect(Collectors.toList());
  }

  public Page<PacienteFilteredResponse> findAll(
      String nome, String telefone, String email, Pageable pageable) {

    return pacienteRepository
        .findByFilters(nome, telefone, email, pageable)
        .map(PacienteMapper::toFilteredResponse);

    //    return pacienteRepository.findAllByNomeContainingAndTelefoneContainingAndEmailContaining(
    //            nome, telefone, email, pageable);
  }

  public PacienteResponse update(Long id, PacienteRequest pacienteRequest) {

    PacienteEntity paciente =
        pacienteRepository
            .findById(id)
            .orElseThrow(
                () -> new EntityNotFoundException("Nenhum paciente encontrado com id: " + id));

    // verifica se request.usuarioId é diferente de paciente.usuarioId:
    if (!pacienteRequest.usuarioId().equals(paciente.getUsuario().getId())) {

      // Valida usuarioId:
      UsuarioEntity novoUsuario = validateRequestId(pacienteRequest.usuarioId());

      paciente.setUsuario(novoUsuario);
    }

    // mapeia usuarioRequest para usuarioEntity:
    updateEntity(pacienteRequest, paciente);

    return toResponse(pacienteRepository.save(paciente));
  }

  public void delete(Long id) {

    if (!pacienteRepository.existsById(id)) { // não faz sentido, mas blz
      throw new EntityNotFoundException("Nenhum paciente encontrado com id: " + id);
    }
    pacienteRepository.deleteById(id);
  }

  private UsuarioEntity validateRequestId(Long usuarioId) {
    // verifica se existe usuario com id do request:
    UsuarioEntity usuario =
        usuarioRepository
            .findById(usuarioId)
            .orElseThrow(
                () ->
                    // new EntityNotFoundException(
                    new IllegalArgumentException("Não existe usuario com id: " + usuarioId));
    // e se usuario é paciente:
    if (!usuario.isPaciente()) {
      throw new IllegalArgumentException(
          "Usuário com id " + usuarioId + " não tem perfil de 'PACIENTE'");
    }

    // if (pacienteRepository.existsByUsuario(usuario)) {
    if (pacienteRepository.existsByUsuario_Id(usuarioId)) {
      throw new DuplicateRequestException("Já existe um paciente cadastrado com este usuário.");
    }
    return usuario;
  }
}
