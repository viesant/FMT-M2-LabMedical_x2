package br.viesant.labmedical_x2.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "perfil")
@Data
public class PerfilEntity implements GrantedAuthority {

  @Id
  //  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  //  private Long id;

  //  @Column(unique = true, nullable = false)
  //  @Enumerated(EnumType.STRING)
  //  private Perfil authority;
  @Column(length = 10)
  private String authority;

  //  @Override
  //  public String getAuthority() {
  //    return authority.toString();
  //  }
}
