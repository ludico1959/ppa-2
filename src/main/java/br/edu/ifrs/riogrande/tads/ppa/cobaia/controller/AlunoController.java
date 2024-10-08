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
import br.edu.ifrs.riogrande.tads.ppa.cobaia.service.AlunoService;
import lombok.RequiredArgsConstructor;


// Controller -> Service (lógica) -> Repository

@RestController
@RequestMapping("/alunos")
@RequiredArgsConstructor
public class AlunoController {

  private final AlunoService alunoService;

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  void newAluno(@RequestBody AlunoDTO body) {

    System.out.println(body);

    alunoService.cadastrarAluno(body);
  }

  // /alunos/20248232/matriculas/ppa-2024-1
  @PostMapping("/{nroMatriculaAluno}/matricular/oferta/{codigoOferta}")
  @ResponseStatus(code = HttpStatus.CREATED)
  void newMatricula(@PathVariable(name = "nroMatriculaAluno") int nroMatriculaAluno,
                    @PathVariable(name = "codigoOferta") String codigoOferta) {

    System.out.println(Map.of(
        "nroMatriculaAluno", nroMatriculaAluno,
        "codigoOferta", codigoOferta));

    alunoService.matricularAluno(nroMatriculaAluno,codigoOferta);

  }
}
