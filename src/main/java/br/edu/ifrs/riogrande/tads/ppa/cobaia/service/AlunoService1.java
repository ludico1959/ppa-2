package br.edu.ifrs.riogrande.tads.ppa.cobaia.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.edu.ifrs.riogrande.tads.ppa.cobaia.dto.AlunoDTO;
import br.edu.ifrs.riogrande.tads.ppa.cobaia.entity.Aluno;
import br.edu.ifrs.riogrande.tads.ppa.cobaia.entity.Matricula;
import br.edu.ifrs.riogrande.tads.ppa.cobaia.entity.Oferta;
import br.edu.ifrs.riogrande.tads.ppa.cobaia.entity.Matricula.Situacao;
import br.edu.ifrs.riogrande.tads.ppa.cobaia.repository.AlunoRepository;
import br.edu.ifrs.riogrande.tads.ppa.cobaia.repository.OfertaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlunoService1 {

  private final AlunoRepository alunoRepository;
  private final OfertaRepository ofertaRepository;
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

  public void matricularAluno(final int numeroMatricula, final String codigoOferta) {

    final Aluno aluno = alunoRepository.findByNumeroMatricula(numeroMatricula)
        .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));
    final Oferta oferta = ofertaRepository.findById(codigoOferta)
        .orElseThrow(() -> new NotFoundException("Oferta não encontrada"));

    int qtdMatriculas = ofertaRepository.countMatriculasByOferta(codigoOferta);
    boolean alunoReincidente = false;

    for (Matricula matricula : aluno.getMatriculas()) {
      if ((matricula.getSituacao() == Situacao.REPROVADO || matricula.getSituacao() == Situacao.TRANCADA
          || matricula.getSituacao() == Situacao.CANCELADA) && matricula.getOferta().getId().equals(oferta.getId())) {
        alunoReincidente = true;
      }
    }

    int totalVagasComOverbook = (int) Math.ceil(oferta.getVagas() * ((int) 1 + oferta.getOverbook()));

    if (alunoReincidente && qtdMatriculas >= totalVagasComOverbook) {
      throw new VagaException();
    }

    if (!alunoReincidente && qtdMatriculas >= oferta.getVagas()) {
      throw new VagaException();
    }

    final Matricula mat = Matricula.builder()
        .id(UUID.randomUUID())
        .data(LocalDateTime.now())
        .oferta(oferta)
        .situacao(Situacao.REGULAR)
        .build();

    aluno.getMatriculas().add(mat);

    alunoRepository.save(aluno);

  }

}
