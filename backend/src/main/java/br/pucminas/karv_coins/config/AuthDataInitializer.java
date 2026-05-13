package br.pucminas.karv_coins.config;

import br.pucminas.karv_coins.model.Aluno;
import br.pucminas.karv_coins.model.Empresa;
import br.pucminas.karv_coins.model.PerfilUsuario;
import br.pucminas.karv_coins.model.Professor;
import br.pucminas.karv_coins.model.Usuario;
import br.pucminas.karv_coins.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AuthDataInitializer {
    @Bean
    CommandLineRunner initializeAuthUsers(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            ensureAluno(usuarioRepository, passwordEncoder);
            ensureProfessor(usuarioRepository, passwordEncoder);
            ensureEmpresa(usuarioRepository, passwordEncoder);
        };
    }

    private void ensureAluno(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        Usuario usuarioExistente = usuarioRepository.findByEmail("aluno@moeda.local").orElse(null);
        if (usuarioExistente instanceof Aluno aluno) {
            aluno.setNome("Aluno Demo");
            aluno.setSenha(passwordEncoder.encode("123456"));
            aluno.setPerfil(PerfilUsuario.ALUNO);
            aluno.setCpf("00000000001");
            aluno.setRg("MG0000001");
            aluno.setCurso("Engenharia de Software");
            aluno.setInstituicao("PUC Minas");
            usuarioRepository.save(aluno);
            return;
        }

        Aluno aluno = new Aluno();
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

    private void ensureProfessor(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        Usuario usuarioExistente = usuarioRepository.findByEmail("professor@moeda.local").orElse(null);
        if (usuarioExistente instanceof Professor professor) {
            professor.setNome("Professor Demo");
            professor.setSenha(passwordEncoder.encode("123456"));
            professor.setPerfil(PerfilUsuario.PROFESSOR);
            professor.setDepartamento("Computacao");
            usuarioRepository.save(professor);
            return;
        }

        Professor professor = new Professor();
        professor.setNome("Professor Demo");
        professor.setEmail("professor@moeda.local");
        professor.setSenha(passwordEncoder.encode("123456"));
        professor.setPerfil(PerfilUsuario.PROFESSOR);
        professor.setDepartamento("Computacao");
        usuarioRepository.save(professor);
    }

    private void ensureEmpresa(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        Usuario usuarioExistente = usuarioRepository.findByEmail("empresa@moeda.local").orElse(null);
        if (usuarioExistente instanceof Empresa empresa) {
            empresa.setNome("Empresa Demo");
            empresa.setSenha(passwordEncoder.encode("123456"));
            empresa.setPerfil(PerfilUsuario.EMPRESA);
            empresa.setCnpj("00000000000100");
            usuarioRepository.save(empresa);
            return;
        }

        Empresa empresa = new Empresa();
        empresa.setNome("Empresa Demo");
        empresa.setEmail("empresa@moeda.local");
        empresa.setSenha(passwordEncoder.encode("123456"));
        empresa.setPerfil(PerfilUsuario.EMPRESA);
        empresa.setCnpj("00000000000100");
        usuarioRepository.save(empresa);
    }
}
