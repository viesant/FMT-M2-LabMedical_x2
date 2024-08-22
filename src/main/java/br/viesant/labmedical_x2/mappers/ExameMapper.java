package br.viesant.labmedical_x2.mappers;

import br.viesant.labmedical_x2.controllers.DTO.ExameRequest;
import br.viesant.labmedical_x2.controllers.DTO.ExameResponse;
import br.viesant.labmedical_x2.entities.ExameEntity;
import br.viesant.labmedical_x2.entities.PacienteEntity;

public class ExameMapper {
  public static ExameResponse toResponse(ExameEntity source) {
    return new ExameResponse(
        source.getId(),
        source.getNome(),
        source.getData(),
        source.getHorario(),
        source.getTipo(),
        source.getLaboratorio(),
        source.getResultado(),
        source.getUrlDocumento(),
        source.getPaciente().getId());
  }

  public static ExameEntity toEntity(ExameRequest source, PacienteEntity paciente) {
    ExameEntity target = new ExameEntity();
    updateEntity(source, target);
    target.setPaciente(paciente);
    return target;
  }

  public static void updateEntity(ExameRequest source, ExameEntity target) {
    target.setNome(source.nome());
    target.setData(source.data());
    target.setHorario(source.horario());
    target.setTipo(source.tipo());
    target.setLaboratorio(source.laboratorio());
    target.setResultado(source.resultado());
    target.setUrlDocumento(source.urlDocumento());
  }
}
