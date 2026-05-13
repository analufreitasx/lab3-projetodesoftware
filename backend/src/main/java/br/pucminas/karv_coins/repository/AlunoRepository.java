package br.pucminas.karv_coins.repository;

import br.pucminas.karv_coins.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Optional<Aluno> findByCpf(String cpf);

    boolean existsByCpf(String cpf);

    boolean existsByRg(String rg);
}
