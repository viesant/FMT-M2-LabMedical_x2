package br.viesant.labmedical_x2.mappers;

import br.viesant.labmedical_x2.controllers.DTO.UsuarioReponse;
import br.viesant.labmedical_x2.controllers.DTO.UsuarioRequest;
import br.viesant.labmedical_x2.entities.PerfilEntity;
import br.viesant.labmedical_x2.entities.UsuarioEntity;
import java.util.List;

public class UsuarioMapper {

  public static UsuarioReponse toResponse(UsuarioEntity source) {
    UsuarioReponse target =
        new UsuarioReponse(
            source.getId(),
            source.getNome(),
            source.getEmail(),
            source.getSenha(),
            source.getCpf(),
            source.getPerfis());
    return target;
  }

  public static UsuarioEntity toEntity(UsuarioRequest source, List<PerfilEntity> perfis) {
    UsuarioEntity target = new UsuarioEntity();

    target.setNome(source.nome());
    target.setEmail(source.email());
    target.setSenha(source.senha());
    target.setDataNascimento(source.dataNascimento());
    target.setCpf(source.cpf());
    target.setPerfis(perfis);

    return target;

    /*
     nome
     email
     senha
     dataNascimento
     cpf
     perfis
    */
  }
}
