package br.viesant.labmedical_x2.repositories;

import br.viesant.labmedical_x2.entities.UsuarioEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
  Optional<UsuarioEntity> findByEmail(String email);

  Boolean existsByEmailOrCpf(String email, String cpf);
  Boolean existsByEmail(String email);
  Boolean existsByCpf(String cpf);


}
