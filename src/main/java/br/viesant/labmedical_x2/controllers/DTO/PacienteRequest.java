package br.viesant.labmedical_x2.controllers.DTO;

import br.viesant.labmedical_x2.enums.EstadoCivil;
import br.viesant.labmedical_x2.enums.Genero;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

public record PacienteRequest(
    @NotBlank(message = "Nome é obrigatório")
        @Size(max = 64, min = 8, message = "Nome deve ter entre 8 e 64 caracteres")
        String nome,
    @NotNull(message = "Gênero é obrigatório") Genero genero,
    @NotNull(message = "Data de nascimento é obrigatória") LocalDate dataNascimento,
    @NotEmpty(message = "CPF é obrigatório")
        @Pattern(
            regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}",
            message = "CPF deve estar no formato xxx.xxx.xxx-xx")
        String cpf,
    @NotBlank(message = "RG é obrigatório")
        @Size(max = 20, message = "RG deve ter no máximo 20 caracteres")
        String rg,
    @NotNull(message = "Estado civil é obrigatório") EstadoCivil estadoCivil,
    @NotBlank(message = "Telefone é obrigatório")
        @Pattern(
            regexp = "\\(\\d{2}\\)\\d{5}-\\d{4}",
            message = "Telefone deve estar no formato (xx)xxxxx-xxxx")
        String telefone,
    @Email(message = "Formato de e-mail inválido") String email,
    @NotBlank(message = "Naturalidade é obrigatória")
        @Size(min = 8, max = 64, message = "Naturalidade deve ter entre 8 e 64 caracteres")
        String naturalidade,
    @NotBlank(message = "Contato de emergência é obrigatório")
        @Pattern(
            regexp = "\\(\\d{2}\\)\\d{5}-\\d{4}",
            message = "Telefone deve estar no formato (xx)xxxxx-xxxx")
        String contatoEmergencia,
    List<String> alergias,
    List<String> cuidadosEspecificos,
    String convenio,
    String numeroConvenio,
    LocalDate validadeConvenio,
    @NotBlank(message = "CEP é obrigatório")
        @Pattern(
            regexp = "\\d{2}\\.\\d{3}-\\d{3}",
            message = "CEP deve estar no formato xx.xxx-xxx")
        String cep,
    String cidade,
    String estado,
    String logradouro,
    String numero,
    String complemento,
    String bairro,
    String pontoReferencia,
    @NotNull(message = "Usuário é obrigatório") Long usuarioId) {}
