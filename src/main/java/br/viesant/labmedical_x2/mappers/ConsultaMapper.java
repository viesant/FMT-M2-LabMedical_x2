package br.viesant.labmedical_x2.mappers;

import br.viesant.labmedical_x2.controllers.DTO.ConsultaRequest;
import br.viesant.labmedical_x2.controllers.DTO.ConsultaResponse;
import br.viesant.labmedical_x2.entities.ConsultaEntity;
import br.viesant.labmedical_x2.entities.PacienteEntity;

public class ConsultaMapper {

  public static ConsultaResponse toResponse(ConsultaEntity source) {
    return new ConsultaResponse(
        source.getMotivo(),
        source.getData(),
        source.getHorario(),
        source.getDescricao(),
        source.getReceita(),
        source.getPrescricao(),
        source.getPaciente().getId());
  }

  public static ConsultaEntity toEntity(ConsultaRequest source, PacienteEntity paciente) {
    ConsultaEntity target = new ConsultaEntity();

    updateEntity(source, target);

    target.setPaciente(paciente);

    return target;
  }

  public static void updateEntity(ConsultaRequest source, ConsultaEntity target) {
    target.setMotivo(source.motivo());
    target.setData(source.data());
    target.setHorario(source.horario());
    target.setDescricao(source.descricao());
    target.setReceita(source.receita());
    target.setPrescricao(source.prescricao());
  }
}
