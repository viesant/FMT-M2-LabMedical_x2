package br.viesant.labmedical_x2.services;

import br.viesant.labmedical_x2.controllers.DTO.ConsultaResponse;
import br.viesant.labmedical_x2.controllers.DTO.ExameResponse;
import br.viesant.labmedical_x2.controllers.DTO.ProntuarioPacienteResponse;
import br.viesant.labmedical_x2.controllers.DTO.ProntuarioResponse;
import br.viesant.labmedical_x2.entities.PacienteEntity;
import br.viesant.labmedical_x2.mappers.ProntuarioMapper;
import br.viesant.labmedical_x2.repositories.ConsultaRepository;
import br.viesant.labmedical_x2.repositories.ExameRepository;
import br.viesant.labmedical_x2.repositories.PacienteRepository;
import br.viesant.labmedical_x2.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProntuarioService {

  private final UsuarioRepository usuarioRepository;
  private final PacienteRepository pacienteRepository;
  private final ConsultaRepository consultaRepository;
  private final ExameRepository exameRepository;

  public Page<ProntuarioResponse> findAll(Long id, String nome, Pageable pageable) {
    return pacienteRepository
        .findByFilters(id, nome, null, null, pageable)
        .map(ProntuarioMapper::toResponse);
  }

  public ProntuarioPacienteResponse findByPacienteId(Long id) {

    PacienteEntity paciente =
        pacienteRepository
            .findById(id)
            .orElseThrow(
                () -> new EntityNotFoundException("Nenhum paciente encontrado com id: " + id));

    List<ExameResponse> exames = exameRepository.findAllByPacienteIdOrderByDataAscHorarioAsc(id);
    List<ConsultaResponse> consultas = consultaRepository.findAllByPacienteIdOrderByDataAscHorarioAsc(id);

    return new ProntuarioPacienteResponse(
        paciente.getId(),
        paciente.getNome(),
        paciente.getConvenio(),
        paciente.getContatoEmergencia(),
        consultas,
        exames);
  }
}
