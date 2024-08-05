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
import br.edu.ifrs.riogrande.tads.ppa.cobaia.entity.Oferta;
import br.edu.ifrs.riogrande.tads.ppa.cobaia.entity.Periodo;
import br.edu.ifrs.riogrande.tads.ppa.cobaia.entity.Matricula.Situacao;
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
  void test() {
    System.out.println("=============================== POST CONSTRUCT ==================");

    Aluno marcio = Aluno.builder()
        .numeroMatricula(LocalDate.now().getYear() * 10000 + 1)
        .nome("Márcio Torres")
        .build();

    alunoRepository.save(marcio);

    System.out.println(alunoRepository.findAll());

    Componente poo = Componente.builder()
        .codigo("poo")
        .nome("Programação Orientada a Objetos")
        .build();

    componenteRepository.save(poo);

    Oferta poo_2024_1 = Oferta.builder()
        .componente(poo)
        .id("poo-2024-1")
        .vagas(10)
        .periodo(Periodo.of(2024, 1))
        .overbook(0.2)
        .build();

    ofertaRepository.save(poo_2024_1);

    Matricula mat = Matricula.builder()
        .id(UUID.randomUUID())
        .data(LocalDateTime.now())
        .oferta(poo_2024_1)
        .situacao(Situacao.REGULAR)
        .build();

    marcio.setMatriculas(List.of(mat));

    alunoRepository.save(marcio);

    System.out.println(alunoRepository.findByNumeroMatricula(20240001).get().getMatriculas());

    System.out.println("=============================== POST CONSTRUCT ==================");
  }

}
