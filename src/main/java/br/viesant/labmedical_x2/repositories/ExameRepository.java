package br.viesant.labmedical_x2.repositories;

import br.viesant.labmedical_x2.controllers.DTO.ConsultaResponse;
import br.viesant.labmedical_x2.controllers.DTO.ExameResponse;
import br.viesant.labmedical_x2.entities.ExameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExameRepository extends JpaRepository<ExameEntity, Long> {
  List<ExameResponse> findAllByPacienteId(Long pacienteId);
  List<ExameResponse> findAllByPacienteIdOrderByDataAsc(Long pacienteId);
  List<ExameResponse> findAllByPacienteIdOrderByDataAscHorarioAsc(Long pacienteId);



  Optional<ExameResponse> findByIdAndPacienteId(Long id, Long id1);
}
