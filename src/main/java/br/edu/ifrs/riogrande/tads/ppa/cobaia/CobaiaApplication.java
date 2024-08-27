package br.edu.ifrs.riogrande.tads.ppa.cobaia;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ifrs.riogrande.tads.ppa.cobaia.entity.Aluno;
import br.edu.ifrs.riogrande.tads.ppa.cobaia.entity.Componente;
import br.edu.ifrs.riogrande.tads.ppa.cobaia.entity.Matricula;
import br.edu.ifrs.riogrande.tads.ppa.cobaia.entity.Matricula.Situacao;
import br.edu.ifrs.riogrande.tads.ppa.cobaia.entity.Oferta;
import br.edu.ifrs.riogrande.tads.ppa.cobaia.entity.Periodo;
import br.edu.ifrs.riogrande.tads.ppa.cobaia.repository.AlunoRepository;
import br.edu.ifrs.riogrande.tads.ppa.cobaia.repository.ComponenteRepository;
import br.edu.ifrs.riogrande.tads.ppa.cobaia.repository.OfertaRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class CobaiaApplication {

  private final AlunoRepository alunoRepository;
  private final ComponenteRepository componenteRepository;
  private final OfertaRepository ofertaRepository;

  public static void main(String[] args) {
    SpringApplication.run(CobaiaApplication.class, args);
  }

  @PostConstruct
  void init() {
    System.out.println("\n-------------------------------------------------------------\n");

    // ALUNOS
    Aluno aluno01 = Aluno.builder()
        .numeroMatricula(LocalDate.now().getYear() * 10000 + 1)
        .nome("LeBron James")
        .build();

    Aluno aluno02 = Aluno.builder()
        .numeroMatricula(LocalDate.now().getYear() * 10000 + 2)
        .nome("Stephen Curry")
        .build();

    Aluno aluno03 = Aluno.builder()
        .numeroMatricula(LocalDate.now().getYear() * 10000 + 3)
        .nome("Kevin Durant")
        .build();

    Aluno aluno04 = Aluno.builder()
        .numeroMatricula(LocalDate.now().getYear() * 10000 + 4)
        .nome("Giannis Antetokounmpo")
        .build();

    alunoRepository.save(aluno01);
    alunoRepository.save(aluno02);
    alunoRepository.save(aluno03);
    alunoRepository.save(aluno04);

    // COMPONENTES
    Componente ppa = Componente.builder()
        .codigo("ppa")
        .nome("Princípios e Padrões de Arquitetura")
        .build();

    componenteRepository.save(ppa);

    Oferta ppa_2024_1 = Oferta.builder()
        .componente(ppa)
        .id("ppa-2024-1")
        .vagas(2)
        .periodo(Periodo.of(2024, 1))
        .overbook(0.5)
        .build();

    ofertaRepository.save(ppa_2024_1);

    Matricula matricula01 = Matricula.builder()
        .id(UUID.randomUUID())
        .data(LocalDateTime.now())
        .oferta(ppa_2024_1)
        .situacao(Situacao.REGULAR)
        .build();

    Matricula matricula02 = Matricula.builder()
        .id(UUID.randomUUID())
        .data(LocalDateTime.now())
        .oferta(ppa_2024_1)
        .situacao(Situacao.REGULAR)
        .build();

    Matricula matricula03 = Matricula.builder()
        .id(UUID.randomUUID())
        .data(LocalDateTime.of(2023, 06, 30, 0, 0, 0))
        .oferta(ppa_2024_1)
        .situacao(Situacao.REPROVADO)
        .build();

    Matricula matricula04 = Matricula.builder()
        .id(UUID.randomUUID())
        .data(LocalDateTime.of(2023, 12, 30, 0, 0, 0))
        .oferta(ppa_2024_1)
        .situacao(Situacao.REPROVADO)
        .build();

    aluno01.setMatriculas(List.of(matricula01)); // matriculado
    aluno02.setMatriculas(List.of(matricula02)); // matriculado
    aluno03.setMatriculas(List.of(matricula03)); // matriculado (overbook)
    aluno04.setMatriculas(List.of(matricula04)); // reprovado

    alunoRepository.save(aluno01);
    alunoRepository.save(aluno02);
    alunoRepository.save(aluno03);
    alunoRepository.save(aluno04);

    System.out.println("\nMATRICULAS:\n" + alunoRepository.findByNumeroMatricula(20240001).get().getMatriculas());

    System.out.println("\n-------------------------------------------------------------\n");
  }

}
