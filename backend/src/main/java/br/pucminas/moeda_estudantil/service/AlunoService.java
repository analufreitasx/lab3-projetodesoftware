package br.pucminas.moeda_estudantil.service;

import br.pucminas.moeda_estudantil.dto.aluno.AlunoResponse;
import br.pucminas.moeda_estudantil.dto.aluno.AtualizarAlunoRequest;
import br.pucminas.moeda_estudantil.dto.aluno.CriarAlunoRequest;
import br.pucminas.moeda_estudantil.model.Aluno;
import br.pucminas.moeda_estudantil.model.PerfilUsuario;
import br.pucminas.moeda_estudantil.repository.AlunoRepository;
import br.pucminas.moeda_estudantil.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AlunoService(AlunoRepository alunoRepository,
                        UsuarioRepository usuarioRepository,
                        PasswordEncoder passwordEncoder) {
        this.alunoRepository = alunoRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }



    public AlunoResponse criar(CriarAlunoRequest request) {
        if (alunoRepository.existsByCpf(request.cpf())) {
            throw new IllegalArgumentException("Já existe um aluno cadastrado com esse CPF.");
        }
        if (alunoRepository.existsByRg(request.rg())) {
            throw new IllegalArgumentException("Já existe um aluno cadastrado com esse RG.");
        }

        if (usuarioRepository.existsByLogin(request.login())) {
            throw new IllegalArgumentException("Esse login já está em uso.");
        }
        if (usuarioRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Esse e-mail já está em uso por outro usuário.");
        }

        Aluno aluno = new Aluno();
        aluno.setNome(request.nome());
        aluno.setLogin(request.login());
        aluno.setSenha(passwordEncoder.encode(request.senha()));
        aluno.setEmail(request.email());
        aluno.setCpf(request.cpf());
        aluno.setRg(request.rg());
        aluno.setCurso(request.curso());
        aluno.setInstituicao(request.instituicao());
        aluno.setEndereco(request.endereco());
        aluno.setPerfil(PerfilUsuario.ALUNO);

        return AlunoResponse.from(alunoRepository.save(aluno));
    }



    @Transactional(readOnly = true)
    public List<AlunoResponse> listarTodos() {
        return alunoRepository.findAll()
                .stream()
                .map(AlunoResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public AlunoResponse buscarPorId(Long id) {
        return AlunoResponse.from(encontrarPorId(id));
    }

    @Transactional(readOnly = true)
    public AlunoResponse buscarPorCpf(String cpf) {
        Aluno aluno = alunoRepository.findByCpf(cpf)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com CPF: " + cpf));
        return AlunoResponse.from(aluno);
    }



    public AlunoResponse atualizar(Long id, AtualizarAlunoRequest request) {
        Aluno aluno = encontrarPorId(id);

        if (request.nome() != null && !request.nome().isBlank()) {
            aluno.setNome(request.nome());
        }
        if (request.email() != null && !request.email().isBlank()) {

            if (!aluno.getEmail().equals(request.email()) && usuarioRepository.existsByEmail(request.email())) {
                throw new IllegalArgumentException("Esse e-mail já está em uso por outro usuário.");
            }
            aluno.setEmail(request.email());
        }
        if (request.senha() != null && !request.senha().isBlank()) {
            aluno.setSenha(passwordEncoder.encode(request.senha()));
        }
        if (request.curso() != null && !request.curso().isBlank()) {
            aluno.setCurso(request.curso());
        }
        if (request.instituicao() != null && !request.instituicao().isBlank()) {
            aluno.setInstituicao(request.instituicao());
        }

        return AlunoResponse.from(alunoRepository.save(aluno));
    }



    public void deletar(Long id) {
        alunoRepository.delete(encontrarPorId(id));
    }



    private Aluno encontrarPorId(Long id) {
        return alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com id: " + id));
    }
}
