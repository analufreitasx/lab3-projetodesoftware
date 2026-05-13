package br.pucminas.moeda_estudantil.service;

import br.pucminas.moeda_estudantil.dto.empresa.AtualizarEmpresaRequest;
import br.pucminas.moeda_estudantil.dto.empresa.CriarEmpresaRequest;
import br.pucminas.moeda_estudantil.dto.empresa.EmpresaResponse;
import br.pucminas.moeda_estudantil.model.Empresa;
import br.pucminas.moeda_estudantil.model.PerfilUsuario;
import br.pucminas.moeda_estudantil.repository.EmpresaRepository;
import br.pucminas.moeda_estudantil.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public EmpresaService(EmpresaRepository empresaRepository,
                          UsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder) {
        this.empresaRepository = empresaRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public EmpresaResponse criar(CriarEmpresaRequest request) {
        if (empresaRepository.existsByCnpj(request.cnpj())) {
            throw new IllegalArgumentException("Já existe uma empresa cadastrada com esse CNPJ.");
        }
        if (usuarioRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Esse e-mail já está em uso por outro usuário.");
        }

        Empresa empresa = new Empresa();
        empresa.setNome(request.nome());
        empresa.setSenha(passwordEncoder.encode(request.senha()));
        empresa.setEmail(request.email());
        empresa.setCnpj(request.cnpj());
        empresa.setPerfil(PerfilUsuario.EMPRESA);

        return EmpresaResponse.from(empresaRepository.save(empresa));
    }


    @Transactional(readOnly = true)
    public List<EmpresaResponse> listarTodos() {
        return empresaRepository.findAll()
                .stream()
                .map(EmpresaResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public EmpresaResponse buscarPorId(Long id) {
        return EmpresaResponse.from(encontrarPorId(id));
    }

    @Transactional(readOnly = true)
    public EmpresaResponse buscarPorCnpj(String cnpj) {
        Empresa empresa = empresaRepository.findByCnpj(cnpj)
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com CNPJ: " + cnpj));
        return EmpresaResponse.from(empresa);
    }


    public EmpresaResponse atualizar(Long id, AtualizarEmpresaRequest request) {
        Empresa empresa = encontrarPorId(id);

        if (request.nome() != null && !request.nome().isBlank()) {
            empresa.setNome(request.nome());
        }
        if (request.email() != null && !request.email().isBlank()) {
            if (!empresa.getEmail().equals(request.email()) && usuarioRepository.existsByEmail(request.email())) {
                throw new IllegalArgumentException("Esse e-mail já está em uso por outro usuário.");
            }
            empresa.setEmail(request.email());
        }
        if (request.senha() != null && !request.senha().isBlank()) {
            empresa.setSenha(passwordEncoder.encode(request.senha()));
        }

        return EmpresaResponse.from(empresaRepository.save(empresa));
    }


    private Empresa encontrarPorId(Long id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada com id: " + id));
    }
}
