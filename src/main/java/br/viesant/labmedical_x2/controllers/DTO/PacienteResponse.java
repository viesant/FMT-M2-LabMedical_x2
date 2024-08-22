package br.viesant.labmedical_x2.controllers.DTO;

public record PacienteResponse(Long id,String nome, int idade, String telefone, String email, String convenio) {}
