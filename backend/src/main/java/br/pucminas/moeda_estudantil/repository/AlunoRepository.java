package br.pucminas.moeda_estudantil.repository;

import br.pucminas.moeda_estudantil.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Optional<Aluno> findByCpf(String cpf);

    boolean existsByCpf(String cpf);

    boolean existsByRg(String rg);
}
