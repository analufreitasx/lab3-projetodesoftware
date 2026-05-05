package br.pucminas.moeda_estudantil.service;

import br.pucminas.moeda_estudantil.dto.LoginRequest;
import br.pucminas.moeda_estudantil.dto.LoginResponse;
import br.pucminas.moeda_estudantil.model.Usuario;
import br.pucminas.moeda_estudantil.repository.UsuarioRepository;
import br.pucminas.moeda_estudantil.security.JwtTokenService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtTokenService;

    public AuthService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            JwtTokenService jwtTokenService
    ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenService = jwtTokenService;
    }

    public LoginResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByLogin(request.login())
                .orElseThrow(() -> new BadCredentialsException("Login ou senha invalidos"));

        if (!passwordEncoder.matches(request.senha(), usuario.getSenha())) {
            throw new BadCredentialsException("Login ou senha invalidos");
        }

        String token = jwtTokenService.generateToken(usuario);

        return new LoginResponse(
                "Bearer",
                token,
                jwtTokenService.getExpirationSeconds(),
                usuario.getPerfil().name()
        );
    }
}
