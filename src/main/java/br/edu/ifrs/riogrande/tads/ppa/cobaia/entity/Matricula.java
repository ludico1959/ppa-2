package br.edu.ifrs.riogrande.tads.ppa.cobaia.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Matricula {

  public enum Situacao {
    REGULAR,
    CANCELADA,
    TRANCADA,
    APROVADO,
    REPROVADO
  }

  @Id
  UUID id;

  @Column(nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  LocalDateTime data;

  @OneToOne(fetch = FetchType.EAGER, optional = false)
  Oferta oferta;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  Situacao situacao;
}
