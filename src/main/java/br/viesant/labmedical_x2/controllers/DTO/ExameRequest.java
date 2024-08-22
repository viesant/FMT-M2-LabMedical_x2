package br.viesant.labmedical_x2.controllers.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;
import java.time.LocalTime;

public record ExameRequest(
        @NotBlank(message = "Nome do exame é obrigatório")
        @Size(min = 8, max = 64, message = "Nome deve ter entre 8 e 64 caracteres")
        String nome,

        @NotNull(message = "Data do exame é obrigatória") LocalDate data,

        @NotNull(message = "Horário do exame é obrigatório") LocalTime horario,

        @NotBlank(message = "Tipo do exame é obrigatório")
        @Size(min = 4, max = 32, message = "Tipo deve ter entre 4 e 32 caracteres")
        String tipo,

        @NotBlank(message = "Laboratório é obrigatório")
        @Size(min = 4, max = 32, message = "Laboratório deve ter entre 4 e 32 caracteres")
        String laboratorio,

        @Size(min = 16, max = 1024, message = "Resultado deve ter entre 16 e 1024 caracteres")
        String resultado,

        @URL(message = "URL do documento deve ser válida")
        String urlDocumento,

        @NotNull(message = "Id do paciente é obrigatório")
        Long pacienteId
) {}
