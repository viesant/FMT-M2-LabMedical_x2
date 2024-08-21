package br.viesant.labmedical_x2.repositories;

import br.viesant.labmedical_x2.entities.PerfilEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<PerfilEntity, String> {
  Optional<PerfilEntity> findByAuthority(String authority);
  Boolean existsByAuthority(String authority);
}
