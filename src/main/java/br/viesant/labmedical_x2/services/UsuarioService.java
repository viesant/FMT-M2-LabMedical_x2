package br.viesant.labmedical_x2.services;

import static br.viesant.labmedical_x2.mappers.UsuarioMapper.*;

import br.viesant.labmedical_x2.controllers.DTO.UsuarioReponse;
import br.viesant.labmedical_x2.controllers.DTO.UsuarioRequest;
import br.viesant.labmedical_x2.entities.PerfilEntity;
import br.viesant.labmedical_x2.entities.UsuarioEntity;
import br.viesant.labmedical_x2.mappers.UsuarioMapper;
import br.viesant.labmedical_x2.repositories.PerfilRepository;
import br.viesant.labmedical_x2.repositories.UsuarioRepository;
import com.sun.jdi.request.DuplicateRequestException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService implements UserDetailsService {

  private final UsuarioRepository usuarioRepository;
  private final PerfilRepository perfilRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return usuarioRepository
        .findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
  }

  public UsuarioReponse create(UsuarioRequest usuarioRequest) {

    // verifica se usuario já foi cadastrado
    if (usuarioRepository.existsByEmail(usuarioRequest.email())) {
      throw new DuplicateRequestException("Já existe um usuário cadastrado com este e-mail.");
    }
    if (usuarioRepository.existsByCpf(usuarioRequest.cpf())) {
      throw new DuplicateRequestException("Já existe um usuário cadastrado com este CPF.");
    }
    //    if (usuarioRepository.existsByEmailOrCpf(usuarioRequest.email(), usuarioRequest.cpf())) {
    //      throw new DuplicateRequestException(
    //          "Já existe um usuário cadastrado com este e-mail ou CPF.");
    //    }

    // verifica se perfil é válido
    List<PerfilEntity> perfis =
        usuarioRequest.perfis().stream()
            .map(
                nome ->
                    perfilRepository
                        .findByAuthority(nome)
                        .orElseThrow(
                            () -> new IllegalArgumentException("Perfil não encontrado: " + nome)))
            .collect(Collectors.toList());

    // mapeia request to entity:
    UsuarioEntity usuario = toEntity(usuarioRequest, perfis);

    // encripta senha:
    usuario.setSenha(passwordEncoder.encode(usuarioRequest.senha()));

    return toResponse(usuarioRepository.save(usuario));
  }

  // para testes:
  public List<UsuarioReponse> findAll() {
    return usuarioRepository.findAll().stream()
        .map(UsuarioMapper::toResponse)
        .collect(Collectors.toList());
  }
}
