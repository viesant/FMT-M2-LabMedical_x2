package br.viesant.labmedical_x2.repositories;

import br.viesant.labmedical_x2.entities.PacienteEntity;
import br.viesant.labmedical_x2.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {
  Boolean existsByUsuario_Id(Long usuarioId);
  Boolean existsByUsuario(UsuarioEntity usuario);
  Optional<PacienteEntity> findByUsuario_Id(Long usuarioId);
  Optional<PacienteEntity> findByUsuario(UsuarioEntity usuario);

  @Query("SELECT p FROM PacienteEntity p JOIN p.usuario u WHERE u.email = :email")
  Optional<PacienteEntity> findByUsuarioEmail(@Param("email") String email);

}
