package br.edu.ifrs.riogrande.tads.ppa.cobaia.controller;

import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrs.riogrande.tads.ppa.cobaia.dto.ComponenteDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/componentes")
public class ComponenteController {

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(code = HttpStatus.CREATED)
  void newComponente(@RequestBody ComponenteDTO body) {

    System.out.println(body);

  }
}
