package br.edu.ifrs.riogrande.tads.ppa.cobaia.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrs.riogrande.tads.ppa.cobaia.dto.ComponenteDTO;
import br.edu.ifrs.riogrande.tads.ppa.cobaia.dto.OfertaDTO;

@RestController
@RequestMapping("/componentes")
public class ComponenteController {

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  void newComponente(@RequestBody ComponenteDTO body) {

    System.out.println(body);

  }

  @PostMapping("/{codigoComponente}/ofertas")
  @ResponseStatus(code = HttpStatus.CREATED)
  void newOferta(@PathVariable String codigoComponente, @RequestBody OfertaDTO body) {

    System.out.println(codigoComponente + " - " + body);

  }
}
