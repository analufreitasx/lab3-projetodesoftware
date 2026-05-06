package br.pucminas.moeda_estudantil.repository;

import br.pucminas.moeda_estudantil.model.Usuario;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByLogin(String login);

    Optional<Usuario> findByEmail(String email);

    boolean existsByLogin(String login);

    boolean existsByEmail(String email);
}
