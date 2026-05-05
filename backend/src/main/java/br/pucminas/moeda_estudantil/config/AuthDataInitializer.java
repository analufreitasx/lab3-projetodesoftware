package br.pucminas.moeda_estudantil.config;

import br.pucminas.moeda_estudantil.model.Aluno;
import br.pucminas.moeda_estudantil.model.Empresa;
import br.pucminas.moeda_estudantil.model.PerfilUsuario;
import br.pucminas.moeda_estudantil.model.Professor;
import br.pucminas.moeda_estudantil.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthDataInitializer {
    @Bean
    CommandLineRunner initializeAuthUsers(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (usuarioRepository.findByLogin("aluno").isEmpty()) {
                Aluno aluno = new Aluno();
                aluno.setLogin("aluno");
                aluno.setNome("Aluno Demo");
                aluno.setEmail("aluno@moeda.local");
                aluno.setSenha(passwordEncoder.encode("123456"));
                aluno.setPerfil(PerfilUsuario.ALUNO);
                aluno.setCpf("00000000001");
                aluno.setRg("MG0000001");
                aluno.setCurso("Engenharia de Software");
                aluno.setInstituicao("PUC Minas");
                usuarioRepository.save(aluno);
            }

            if (usuarioRepository.findByLogin("professor").isEmpty()) {
                Professor professor = new Professor();
                professor.setLogin("professor");
                professor.setNome("Professor Demo");
                professor.setEmail("professor@moeda.local");
                professor.setSenha(passwordEncoder.encode("123456"));
                professor.setPerfil(PerfilUsuario.PROFESSOR);
                professor.setDepartamento("Computacao");
                usuarioRepository.save(professor);
            }

            if (usuarioRepository.findByLogin("empresa").isEmpty()) {
                Empresa empresa = new Empresa();
                empresa.setLogin("empresa");
                empresa.setNome("Empresa Demo");
                empresa.setEmail("empresa@moeda.local");
                empresa.setSenha(passwordEncoder.encode("123456"));
                empresa.setPerfil(PerfilUsuario.EMPRESA);
                empresa.setCnpj("00000000000100");
                usuarioRepository.save(empresa);
            }
        };
    }
}
