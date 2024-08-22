package br.viesant.labmedical_x2.repositories;

import br.viesant.labmedical_x2.controllers.DTO.ConsultaResponse;
import br.viesant.labmedical_x2.entities.ConsultaEntity;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Long> {
  List<ConsultaResponse> findAllByPacienteId(Long pacienteId);

  Optional<ConsultaResponse> findByIdAndPacienteId(Long id, Long id1);
}
