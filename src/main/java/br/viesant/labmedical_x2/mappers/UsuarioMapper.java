package br.viesant.labmedical_x2.mappers;

import br.viesant.labmedical_x2.controllers.DTO.UsuarioRequest;
import br.viesant.labmedical_x2.entities.UsuarioEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UsuarioMapper {

  public static UsuarioEntity toEntity(UsuarioRequest source) {
    UsuarioEntity target = new UsuarioEntity();

    target.setNome(source.nome());
    target.setEmail(source.email());
    target.setSenha(source.senha());
    target.setDataNascimento(source.dataNascimento());
    target.setCpf(source.cpf());
    return target;

    /*
     nome
     email
     senha
     dataNascimento
     cpf
     perfis -> este fica fora do mapper
    */

  }
}
