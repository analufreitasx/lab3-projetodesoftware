package br.pucminas.moeda_estudantil.repository;

import br.pucminas.moeda_estudantil.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Optional<Aluno> findByCpf(String cpf);

    Optional<Aluno> findByRg(String rg);

    Optional<Aluno> findByLogin(String login);

    Optional<Aluno> findByEmail(String email);

    boolean existsByCpf(String cpf);

    boolean existsByRg(String rg);

    List<Aluno> findAllByInstituicaoIgnoreCase(String instituicao);

    List<Aluno> findAllByCursoIgnoreCase(String curso);
}
