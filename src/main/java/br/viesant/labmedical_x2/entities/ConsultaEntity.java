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
@Table(name = "consulta")
@Data
public class ConsultaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 64)
  private String motivo;

  @Column(nullable = false)
  private LocalDate data;

  @Column(nullable = false)
  private LocalTime horario;

  @Column(nullable = false, length = 1024)
  private String descricao;

  private String receita;
  private String prescricao;

  @ManyToOne
  @JoinColumn(name = "paciente_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  private PacienteEntity paciente;

  /*
  ■ Motivo da consulta: Obrigatório, com máximo e mínimo de 64 e 8
  caracteres, respectivamente.
  ■ Data da Consulta: Obrigatório. Dica: LocalDate.
  ■ Horário da Consulta: Obrigatório. Dica: LocalTime.
  ■ Descrição do Problema: Obrigatório, com máximo e mínimo de 1024 e
  16 caracteres, respectivamente.
  ■ Medicação Receitada: Não obrigatório.
  ■ Dosagem e Precauções: Não obrigatório, com máximo e mínimo de 256
  e 16 caracteres, respectivamente.
  ■ Id do paciente: Obrigatório. Identificador do paciente consultado.
     */
}
