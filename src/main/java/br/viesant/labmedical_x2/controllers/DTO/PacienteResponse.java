package br.viesant.labmedical_x2.controllers.DTO;

import br.viesant.labmedical_x2.enums.Genero;

import java.time.LocalDate;

public record PacienteResponse(
    Long id,
    String nome,
    Genero genero,
    LocalDate dataNascimento,
    String cpf,
    String rg,
    String telefone,
    String naturalidade,
    String contatoEmergencia,
    String convenio,
    String numeroConvenio,
    LocalDate validadeConvenio,
    String cep,
    String cidade,
    String estado,
    String logradouro,
    String numero,
    String complemento,
    String bairro,
    String pontoReferencia,
    Long usuarioId) {}
