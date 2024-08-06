package br.edu.ifrs.riogrande.tads.ppa.cobaia.service;

import java.time.LocalDate;
import java.util.Random;

import org.springframework.stereotype.Service;

import br.edu.ifrs.riogrande.tads.ppa.cobaia.dto.AlunoDTO;
import br.edu.ifrs.riogrande.tads.ppa.cobaia.entity.Aluno;
import br.edu.ifrs.riogrande.tads.ppa.cobaia.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlunoService1 {

  private final AlunoRepository alunoRepository;

  // Transaction Script
  // Data Transfer Object (objeto de transferência de dados)

  public void cadastrarAluno(AlunoDTO dto) {
    Aluno aluno = new Aluno();
    aluno.setNome(dto.getNome()); // de-para
    // aluno.setMatriculas(List.of());
    // 20240000

    // ----- lógica de negócio para criar um aluno
    // 20248232
    Random rand = new Random();
    int ano = LocalDate.now().getYear();
    int id = rand.nextInt(10000);
    int numeroMatricula = ano * 10000 + id;
    aluno.setNumeroMatricula(numeroMatricula);
    // ----

    alunoRepository.save(aluno);
  }

}
