package br.viesant.labmedical_x2.controllers.DTO;

import br.viesant.labmedical_x2.entities.PerfilEntity;

import java.util.Collection;

public record UsuarioReponse(
        Long id,
        String nome,
        String email,
        String senha,
        String cpf,
        Collection<PerfilEntity> perfis
) {}
