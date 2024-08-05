package br.edu.ifrs.riogrande.tads.ppa.cobaia.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Periodo {

  @Column(nullable = false)
  private int ano;

  @Column(nullable = false)
  private int metade;

  public static Periodo of(int ano, int metade) {
    final Periodo p = new Periodo();
    p.setAno(ano);
    p.setMetade(metade);
    return p;
  }

}
