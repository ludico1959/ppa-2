package br.edu.ifrs.riogrande.tads.ppa.cobaia.entity;

import java.util.ArrayList;
import java.util.List;

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

}
