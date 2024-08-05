package br.edu.ifrs.riogrande.tads.ppa.cobaia.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.edu.ifrs.riogrande.tads.ppa.cobaia.entity.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, Integer> {

  Optional<Aluno> findByNumeroMatricula(int numeroMatricula);

}
