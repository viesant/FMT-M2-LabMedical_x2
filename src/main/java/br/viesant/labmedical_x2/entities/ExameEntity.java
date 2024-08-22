package br.viesant.labmedical_x2.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "exame")
@Data
public class ExameEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 64)
  private String nome;

  @Column(nullable = false)
  private LocalDate data;

  @Column(nullable = false)
  private LocalTime horario;

  @Column(nullable = false, length = 32)
  private String tipo;

  @Column(nullable = false, length = 32)
  private String laboratorio;

  private String urlDocumento;

  @Column(length = 1024)
  private String resultado;

  @ManyToOne
  @JoinColumn(name = "paciente_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private PacienteEntity paciente;

  /*
  ■ Nome do Exame: Obrigatório, com máximo e mínimo de 64 e 8
  caracteres, respectivamente.
  ■ Data do Exame: Obrigatório. Dica: LocalDate.
  ■ Horário do Exame: Obrigatório. Dica: LocalTime.
  ■ Tipo do Exame: Obrigatório, com máximo e mínimo de 32 e 4 caracteres,
  respectivamente.
  ■ Laboratório: Obrigatório, com máximo e mínimo de 32 e 4 caracteres,
  respectivamente.
  ■ URL do Documento: Não obrigatório.
  ■ Resultados: Não Obrigatório, com máximo e mínimo de 1024 e 16
  caracteres, respectivamente.
  ■ Id do paciente: Obrigatório. Identificador do paciente examinado.
     */

}
