package br.viesant.labmedical_x2.controllers.DTO;

public record DashboardResponse(
        Long qtdPacientes,
        Long qtdConsultas,
        Long qtdExames
) {}
