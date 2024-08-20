package br.viesant.labmedical_x2.configs;

import br.viesant.labmedical_x2.entities.PerfilEntity;
import br.viesant.labmedical_x2.entities.UsuarioEntity;
import br.viesant.labmedical_x2.repositories.PerfilRepository;
import br.viesant.labmedical_x2.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Collections;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@AllArgsConstructor
public class StartUserConfig implements CommandLineRunner {

  private final UsuarioRepository usuarioRepository;
  private final PerfilRepository perfilRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public void run(String... args) throws Exception {

    salvaPerfil("ADMIN");
    salvaPerfil("MEDICO");
    salvaPerfil("PACIENTE");

    if( usuarioRepository.findByEmail("admin@viesant.com").isEmpty()){

      UsuarioEntity admin = new UsuarioEntity();
      admin.setNome("admin");
      admin.setEmail("admin@viesant.com"); //username
      admin.setSenha(passwordEncoder.encode("admin")); //password

      admin.setDataNascimento(LocalDate.now());
      admin.setCpf("123.456.789-99");

      PerfilEntity perfilAdmin = perfilRepository.findByNome("ADMIN").orElseThrow(
              () -> new EntityNotFoundException("Perfil ADMIN não existe!")
//              () -> new RuntimeException("Perfil ADMIN não existe!")
      );

      admin.setPerfis(Collections.singleton(perfilAdmin));
      usuarioRepository.save(admin);


    }

  }

  private void salvaPerfil(String perfil) {
    if (perfilRepository.findByNome(perfil).isEmpty()) {
      PerfilEntity novoPerfil = new PerfilEntity();
      novoPerfil.setNome(perfil);
      perfilRepository.save(novoPerfil);
    }
  }
}
