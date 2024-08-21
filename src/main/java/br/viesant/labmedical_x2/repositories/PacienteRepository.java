package br.viesant.labmedical_x2.repositories;

import br.viesant.labmedical_x2.entities.PacienteEntity;
import br.viesant.labmedical_x2.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {
  Boolean existsByUsuario_Id(Long usuarioId);
  Boolean existsByUsuario(UsuarioEntity usuario);
}
