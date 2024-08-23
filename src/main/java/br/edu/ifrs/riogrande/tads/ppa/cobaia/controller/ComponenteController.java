package br.edu.ifrs.riogrande.tads.ppa.cobaia.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrs.riogrande.tads.ppa.cobaia.dto.ComponenteDTO;
import br.edu.ifrs.riogrande.tads.ppa.cobaia.dto.OfertaDTO;
import br.edu.ifrs.riogrande.tads.ppa.cobaia.entity.Componente;
import br.edu.ifrs.riogrande.tads.ppa.cobaia.service.ComponenteService1;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/componentes")
@RequiredArgsConstructor
public class ComponenteController {

  private final ComponenteService1 componenteService;;

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  void newComponente(@RequestBody ComponenteDTO body) {

    System.out.println(body);;;;;;

    componenteService.cadastrarComponente(body);

  }

  @GetMapping
  //@ResponseStatus(code = HttpStatus.OK)
  ResponseEntity <?> getComponente() {

    Iterable<Componente> lista = componenteService.findComponentes();

    return ResponseEntity.ok(lista);

  }


  @PostMapping("/{codigoComponente}/ofertas")
  @ResponseStatus(code = HttpStatus.CREATED)
  void newOferta(@PathVariable (name = "codigoComponente") String codigoComponente, @RequestBody OfertaDTO body) {

    System.out.println(codigoComponente + " - " + body);

    componenteService.cadastrarOferta(codigoComponente, body);

  }
}
