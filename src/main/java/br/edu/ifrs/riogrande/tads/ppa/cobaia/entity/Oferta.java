package br.edu.ifrs.riogrande.tads.ppa.cobaia.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
public class Oferta {

  @Id
  String id;

  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "ano", column = @Column(name = "periodo_ano")),
      @AttributeOverride(name = "metade", column = @Column(name = "periodo_metade"))
  })
  Periodo periodo;

  @Column(nullable = false)
  int vagas;

  @Column(nullable = true)
  double overbook;

  @OneToOne(optional = false, fetch = FetchType.EAGER)
  Componente componente;

}
