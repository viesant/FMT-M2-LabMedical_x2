package br.viesant.labmedical_x2.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Collection;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "usuario")
@Data
public class UsuarioEntity implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nome;

  @Column(unique = true, nullable = false)
  private String email;

  @Column(nullable = false)
  private String senha;

  @Column(unique = true, nullable = false)
  private String cpf;

  @Column(nullable = false)
  private LocalDate dataNascimento;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "usuarios_perfis",
      joinColumns = @JoinColumn(name = "usuario_id"),
      inverseJoinColumns = @JoinColumn(name = "perfis_id"))
  private Collection<PerfilEntity> perfis;

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public String getPassword() {
    return senha;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return perfis;
  }

  public Boolean isPaciente() {
    return perfis.stream().anyMatch(perfil -> perfil.getAuthority().equals("PACIENTE"));
  }

  public Boolean isAdminOrMedico() {
    return perfis.stream()
        .anyMatch(
            perfil ->
                perfil.getAuthority().equals("ADMIN") || perfil.getAuthority().equals("MEDICO"));
  }
}
