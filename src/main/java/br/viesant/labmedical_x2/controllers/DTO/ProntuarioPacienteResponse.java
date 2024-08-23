package br.viesant.labmedical_x2.controllers.DTO;

import java.util.List;

public record ProntuarioPacienteResponse(
    Long id,
    String nome,
    String convenio,
    String contatoEmergencia,
    List<ConsultaResponse> consultas,
    List<ExameResponse> exames
) {}
