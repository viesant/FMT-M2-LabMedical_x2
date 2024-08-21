package br.viesant.labmedical_x2.controllers.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

public record UsuarioRequest(
    @NotEmpty(message = "Nome é obrigatório")
        @Size(max = 255, message = "Nome deve ter no máximo 255 caracteres")
        String nome,
    @NotEmpty(message = "Email é obrigatório")
        @Email(message = "Email deve ser válido")
        @Size(max = 255, message = "Email deve ter no máximo 255 caracteres")
        String email,
    @NotEmpty(message = "Senha é obrigatória")
        @Size(max = 255, message = "Senha deve ter no máximo 255 caracteres")
        String senha,
    @NotNull(message = "Data de nascimento é obrigatória") LocalDate dataNascimento,
    @NotEmpty(message = "CPF é obrigatório")
        @Pattern(
            regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}",
            message = "CPF deve estar no formato xxx.xxx.xxx-xx")
        String cpf,
    @NotNull(message = "Perfil é obrigatório") List<String> perfis) {}
