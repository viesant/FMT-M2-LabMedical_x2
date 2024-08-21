package br.viesant.labmedical_x2.entities;

import br.viesant.labmedical_x2.enums.EstadoCivil;
import br.viesant.labmedical_x2.enums.Genero;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Entity
@Table(name = "paciente")
@Data
public class PacienteEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 64)
  private String nome;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Genero genero;

  @Column(nullable = false)
  private LocalDate dataNascimento;

  @Column(nullable = false, length = 14) // 999.999.999-99
  private String cpf;

  @Column(nullable = false, length = 20)
  private String rg;

  @Column(nullable = false)
  private EstadoCivil estadoCivil;

  @Column(nullable = false, length = 14) // (99)99999-9999
  private String telefone;

  private String email;

  @Column(nullable = false, length = 64)
  private String naturalidade;

  @Column(nullable = false, length = 14) // (99)99999-9999
  private String contatoEmergencia;

  private List<String> alergias;
  private List<String> cuidadosEspecificos;

  @Column private String convenio;

  @Column private String numeroConvenio;

  @Column private LocalDate validadeConvenio;

  @Column(nullable = false, length = 10) // 99.999-999
  private String cep;

  private String cidade;
  private String estado;
  private String logradouro;
  private String numero;
  private String complemento;
  private String bairro;
  private String pontoReferencia;

  @OneToOne
  @JoinColumn(name = "usuario_id", nullable = false)
  private UsuarioEntity usuario;

  /*
    Dados do paciente:
  ■ Nome Completo: Obrigatório, com máximo e mínimo de 64 e 8
  caracteres, respectivamente.
  ■ Gênero: Obrigatório.
  ■ Data de Nascimento: Obrigatório, data válida.
  ■ CPF: Obrigatório com o formato 000.000.000-00
  ■ RG com órgão expedidor: Obrigatório, com máximo de 20 caracteres.
  ■ Estado Civil: Obrigatório.
  ■ Telefone: Obrigatório com o formato (99) 9 9999-9999
  ■ E-mail: Não obrigatório e com validação de formato.
  ■ Naturalidade: Obrigatório, com máximo e mínimo de 64 e 8 caracteres,
  respectivamente.
  ■ Contato de Emergência: Obrigatório com o formato (99) 9 9999-9999
  ■ Lista de Alergias: Não obrigatório.
  ■ Lista de Cuidados Específicos: Não obrigatório.
  ■ Convênio: Não obrigatório.
  ■ Número do Convênio: Não obrigatório.
  ■ Validade do Convênio: Não obrigatório.
  ■ Endereço: Cep, Cidade, Estado, Logradouro, Número, Complemento,
  Bairro e Ponto de Referência.
  ■ id_usuario: Identificador do usuário de acesso do paciente, esse usuário
  já deve estar criado e com o perfil de paciente.
     */

}
