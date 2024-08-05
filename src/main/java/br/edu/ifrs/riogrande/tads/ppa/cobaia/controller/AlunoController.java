package br.edu.ifrs.riogrande.tads.ppa.cobaia.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifrs.riogrande.tads.ppa.cobaia.dto.AlunoDTO;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  void newAluno(@RequestBody AlunoDTO body) {

    System.out.println(body);

  }

  @PostMapping("/{numeroMatricula}/matriculas/{codigoOferta}")
  @ResponseStatus(code = HttpStatus.CREATED)

  void newAluno(@PathVariable String numeroMatricula, @PathVariable String codigoOferta) {
    System.out.println(Map.of(
        "numeroMatricula", numeroMatricula,
        "codigoOferta", codigoOferta));
  }
}
