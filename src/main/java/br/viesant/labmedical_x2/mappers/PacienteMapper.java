package br.viesant.labmedical_x2.mappers;

import br.viesant.labmedical_x2.controllers.DTO.PacienteRequest;
import br.viesant.labmedical_x2.controllers.DTO.PacienteResponse;
import br.viesant.labmedical_x2.entities.PacienteEntity;
import br.viesant.labmedical_x2.entities.UsuarioEntity;

import java.time.LocalDate;
import java.time.Period;

public class PacienteMapper {

  public static PacienteResponse toResponse(PacienteEntity source) {

    // calcula Idade:
    int idade = Period.between(source.getDataNascimento(), LocalDate.now()).getYears();

    return new PacienteResponse(
        source.getId(), source.getNome(),idade, source.getTelefone(), source.getEmail(),
            source.getConvenio());
  }

  public static PacienteEntity toEntity(PacienteRequest source, UsuarioEntity usuario) {
    PacienteEntity target = new PacienteEntity();

    updateEntity(source, target);

    target.setUsuario(usuario);

    return target;
  }

  public static void updateEntity(PacienteRequest source, PacienteEntity target) {

    target.setNome(source.nome());
    target.setGenero(source.genero());
    target.setDataNascimento(source.dataNascimento());
    target.setCpf(source.cpf());
    target.setRg(source.rg());
    target.setEstadoCivil(source.estadoCivil());
    target.setTelefone(source.telefone());
    target.setEmail(source.email());
    target.setNaturalidade(source.naturalidade());
    target.setContatoEmergencia(source.contatoEmergencia());
    target.setAlergias(source.alergias());
    target.setCuidadosEspecificos(source.cuidadosEspecificos());
    target.setConvenio(source.convenio());
    target.setNumeroConvenio(source.numeroConvenio());
    target.setValidadeConvenio(source.validadeConvenio());
    target.setCep(source.cep());
    target.setCidade(source.cidade());
    target.setEstado(source.estado());
    target.setLogradouro(source.logradouro());
    target.setNumero(source.numero());
    target.setComplemento(source.complemento());
    target.setBairro(source.bairro());
    target.setPontoReferencia(source.pontoReferencia());
  }
}
