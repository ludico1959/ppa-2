package br.edu.ifrs.riogrande.tads.ppa.cobaia.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.edu.ifrs.riogrande.tads.ppa.cobaia.entity.Aluno;
import br.edu.ifrs.riogrande.tads.ppa.cobaia.entity.Matricula;
import br.edu.ifrs.riogrande.tads.ppa.cobaia.entity.Oferta;
import br.edu.ifrs.riogrande.tads.ppa.cobaia.entity.Matricula.Situacao;
import br.edu.ifrs.riogrande.tads.ppa.cobaia.repository.AlunoRepository;
import br.edu.ifrs.riogrande.tads.ppa.cobaia.repository.OfertaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatriculaService {

  private final AlunoRepository alunoRepository;
  private final OfertaRepository ofertaRepository;

  public void realizarMatricula(int numeroMatricula,
                                String codigoOfertaComponente) {

    Aluno aluno = alunoRepository.findByNumeroMatricula(numeroMatricula).orElseThrow();
    Oferta oferta = ofertaRepository.findById(codigoOfertaComponente).orElseThrow();

    // if (oferta.getVagas() < numeroMatriculados)
    // else
    //    o aluno já fez o componente e não concluiu
    //    matricular se houver espaço no overbooking
    //    aluno matriculado há mais de 3 anos

    Matricula matricula = Matricula.builder()
        .id(UUID.randomUUID())
        .data(LocalDateTime.now())
        .oferta(oferta)
        .situacao(Situacao.REGULAR)
        .build();

    aluno.getMatriculas().add(matricula);

    alunoRepository.save(aluno);

  }

}
