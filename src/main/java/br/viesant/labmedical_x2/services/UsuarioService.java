package br.viesant.labmedical_x2.services;

import br.viesant.labmedical_x2.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UsuarioService implements UserDetailsService {

  private final UsuarioRepository usuarioRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return usuarioRepository
        .findByEmail(username)
        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
  }
}
