package br.viesant.labmedical_x2.repositories;

import br.viesant.labmedical_x2.entities.PacienteEntity;
import br.viesant.labmedical_x2.entities.UsuarioEntity;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {
  Boolean existsByUsuario_Id(Long usuarioId);

  Boolean existsByUsuario(UsuarioEntity usuario);

  Optional<PacienteEntity> findByUsuario_Id(Long usuarioId);

  Optional<PacienteEntity> findByUsuario(UsuarioEntity usuario);

  Page<PacienteEntity> findAllByNomeContainingAndTelefoneContainingAndEmailContaining(
      String nome, String telefone, String email, Pageable pageable);

  @Query(
      "SELECT p FROM PacienteEntity p "
          + "WHERE (:nome IS NULL OR p.nome ILIKE %:nome%) "
          + "AND (:telefone IS NULL OR p.telefone ILIKE %:telefone%) "
          + "AND (:email IS NULL OR p.email ILIKE %:email%)")
  Page<PacienteEntity> findByFilters(
      @Param("nome") String nome,
      @Param("telefone") String telefone,
      @Param("email") String email,
      Pageable pageable);
}
