package br.viesant.labmedical_x2.controllers.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(
    @NotEmpty(message = "Usuário é obrigatório")
    @Email(message = "O usuário deve ser um email válido")
    String username,

    @NotEmpty(message = "Senha é obrigatória")
    String password) {}
