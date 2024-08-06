package br.edu.ifrs.riogrande.tads.ppa.cobaia.service;

import org.springframework.stereotype.Service;

import br.edu.ifrs.riogrande.tads.ppa.cobaia.dto.AlunoDTO;
import br.edu.ifrs.riogrande.tads.ppa.cobaia.entity.Aluno;
import br.edu.ifrs.riogrande.tads.ppa.cobaia.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlunoService2 {

  private final AlunoRepository alunoRepository;

  public void cadastrarAluno(AlunoDTO dto) {
    // O "de-para" é parte da aplicação, não do domínio

    alunoRepository.save(Aluno.cadastrar(dto.getNome())); // one-liner

    // Aluno aluno = new Aluno();
    // aluno.setNome(dto.getNome());
    // aluno.gerarNumeroMatricula();
    // alunoRepository.save(aluno);
  }

}
