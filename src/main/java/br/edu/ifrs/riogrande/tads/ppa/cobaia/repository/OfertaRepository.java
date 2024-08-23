package br.edu.ifrs.riogrande.tads.ppa.cobaia.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.edu.ifrs.riogrande.tads.ppa.cobaia.entity.Oferta;

public interface OfertaRepository extends CrudRepository<Oferta, String> {

  @Query("SELECT COUNT(m) FROM Matricula m WHERE m.oferta.id = :codigoOferta AND m.situacao = 'REGULAR'")
  int countMatriculasByOferta(@Param("codigoOferta") String codigoOferta);

}
