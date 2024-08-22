package br.viesant.labmedical_x2.controllers.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public record ConsultaResponse(
    Long id,
    String motivo,
    LocalDate data,
    LocalTime horario,
    String descricao,
    String receita,
    String prescricao,
    Long pacienteId) {}
