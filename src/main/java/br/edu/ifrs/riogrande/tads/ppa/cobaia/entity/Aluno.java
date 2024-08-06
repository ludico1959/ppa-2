package br.edu.ifrs.riogrande.tads.ppa.cobaia.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Aluno {

  @Id
  int numeroMatricula;

  @Column(nullable = false)
  String nome;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @Builder.Default
  List<Matricula> matriculas = new ArrayList<>();

  // Domain-Driven: o próprio objeto de domínio
  // abriga a lógica
  public void gerarNumeroMatricula() {
    Random rand = new Random();
    int ano = LocalDate.now().getYear();
    int id = rand.nextInt(10000);
    int numeroMatricula = ano * 10000 + id;
    this.setNumeroMatricula(numeroMatricula);
  }

  // static factory method: método fábrica estático
  public static Aluno cadastrar(String nome) {
    Aluno aluno = new Aluno();
    aluno.setNome(nome);
    aluno.gerarNumeroMatricula();
    return aluno;
  }

}
