package br.viesant.labmedical_x2.mappers;

import br.viesant.labmedical_x2.controllers.DTO.PacienteRequest;
import br.viesant.labmedical_x2.entities.PacienteEntity;
import br.viesant.labmedical_x2.entities.UsuarioEntity;

public class PacienteMapper {

  public static PacienteEntity toEntity(PacienteRequest source, UsuarioEntity usuario) {
    PacienteEntity target = new PacienteEntity();

    target.setNome(source.nome());

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

    target.setUsuario(usuario);

    return target;
    /*
    nome
    genero
    dataNascimento
    cpf
    rg
    estadoCivil
    telefone
    email
    naturalidade
    contatoEmergencia
    alergias
    cuidadosEspecificos
    convenio
    numeroConvenio
    validadeConvenio
    cep
    cidade
    estado
    logradouro
    numero
    complemento
    bairro
    pontoReferencia
    usuarioId
    */
  }
}
