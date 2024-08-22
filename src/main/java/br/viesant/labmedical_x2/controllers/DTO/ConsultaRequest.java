package br.viesant.labmedical_x2.controllers.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

public record ConsultaRequest(
        @NotBlank(message = "Motivo da consulta é obrigatório")
        @Size(min = 8, max = 64, message = "Motivo da consulta deve ter entre 8 e 64 caracteres")
        String motivo,

        @NotNull(message = "Data da consulta é obrigatória") LocalDate data,

        @NotNull(message = "Horário da consulta é obrigatório") LocalTime horario,

        @NotBlank(message = "Descrição do problema é obrigatória")
        @Size(min = 16, max = 1024, message = "Descrição deve ter entre 16 e 1024 caracteres")
        String descricao,

        @Size(max = 255, message = "Medicação receitada deve ter no máximo 255 caracteres")
        String receita,

        @Size(min = 16, max = 255, message = "Dosagem e precauções deve ter entre 16 e 255 caracteres")
        String prescricao,

        @NotNull(message = "Id do paciente é obrigatório")
        Long pacienteId
) {}
