package br.viesant.labmedical_x2.services;

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
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UsuarioService implements UserDetailsService {

  private final UsuarioRepository usuarioRepository;
  private final PerfilRepository perfilRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return usuarioRepository
        .findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
  }

  public UsuarioEntity create(UsuarioRequest usuarioRequest) {

    // verifica se usuario já foi cadastrado
    if (usuarioRepository.existsByEmailOrCpf(usuarioRequest.email(), usuarioRequest.cpf())) {
      throw new DuplicateRequestException(
          "Já existe um usuário cadastrado com este e-mail ou CPF.");
    }

    // verifica se perfil é válido
    List<PerfilEntity> perfis =
        usuarioRequest.perfis().stream()
            .map(
                nome ->
                    perfilRepository
                        .findByNome(nome)
                        .orElseThrow(
                            () -> new IllegalArgumentException("Perfil não encontrado: " + nome)))
            .collect(Collectors.toList());

    UsuarioEntity usuario = UsuarioMapper.toEntity(usuarioRequest);
    usuario.setPerfis(perfis);

    return usuarioRepository.save(usuario);
  }

  public List<UsuarioEntity> findAll() {
    return usuarioRepository.findAll();
  }
}
