package br.edu.ifrs.riogrande.tads.ppa.cobaia.service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import br.edu.ifrs.riogrande.tads.ppa.cobaia.dto.ComponenteDTO;
import br.edu.ifrs.riogrande.tads.ppa.cobaia.dto.OfertaDTO;
import br.edu.ifrs.riogrande.tads.ppa.cobaia.entity.Componente;
import br.edu.ifrs.riogrande.tads.ppa.cobaia.entity.Oferta;
import br.edu.ifrs.riogrande.tads.ppa.cobaia.entity.Periodo;
import br.edu.ifrs.riogrande.tads.ppa.cobaia.repository.ComponenteRepository;
import br.edu.ifrs.riogrande.tads.ppa.cobaia.repository.OfertaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComponenteService1 {

  private final ComponenteRepository componenteRepository;
  private final OfertaRepository ofertaRepository;

  public void cadastrarComponente(ComponenteDTO dto) {
    // Princípios e Padrões de Arquitetura pepda
    String codigo = Stream.of(dto.getNome() // Princípios e Padrões de Arquitetura
        .toLowerCase() // princípios e padrões de arquitetura
        .split("\\s+")) // [princípios, e, padrões, de, arquitetura,]
        .filter(p -> p.length() > 2) // [princípios, padrões, arquitetura]
        .map(s -> s.charAt(0) + "") // [p, p, a]
        .collect(Collectors.joining("")); // "ppa"

    Componente componente = new Componente(codigo, dto.getNome());

    // poderia deixar a logica para o componente (domain object)
    // Componente.cadastrar(dto.getNome());

    componenteRepository.save(componente);
  }

  public void cadastrarOferta(String codigoComponente, OfertaDTO dto) {

    Componente componente = componenteRepository
        .findById(codigoComponente).orElseThrow();

    // lógica de negócio: transaction script
    String id = "%s-%d-%d".formatted(
        componente.getCodigo(),
        dto.getPeriodoAno(),
        dto.getPeriodoMetade());

    Oferta oferta = Oferta.builder()
        .componente(componente)
        .id(id)
        .vagas(dto.getVagas())
        .periodo(Periodo.of(dto.getPeriodoAno(), dto.getPeriodoMetade()))
        .overbook(dto.getOverbook())
        .build();

    ofertaRepository.save(oferta);
  }

    public Iterable<Componente> findComponentes() {

      return componenteRepository.findAll();
  }
}
