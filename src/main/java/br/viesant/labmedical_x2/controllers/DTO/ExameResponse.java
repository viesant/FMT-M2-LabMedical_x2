package br.viesant.labmedical_x2.controllers.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public record ExameResponse(
    Long id,
    String nome,
    LocalDate data,
    LocalTime horario,
    String tipo,
    String laboratorio,
    String resultado,
    String urlDocumento,
    Long pacienteId) {}
