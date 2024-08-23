package br.viesant.labmedical_x2.mappers;

import br.viesant.labmedical_x2.controllers.DTO.ProntuarioResponse;
import br.viesant.labmedical_x2.entities.PacienteEntity;

public class ProntuarioMapper {

  public static ProntuarioResponse toResponse(PacienteEntity source) {
    return new ProntuarioResponse(source.getId(), source.getNome(), source.getConvenio());
  }
}
