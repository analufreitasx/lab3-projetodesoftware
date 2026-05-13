package br.pucminas.moeda_estudantil.service;

import br.pucminas.moeda_estudantil.dto.request.LoginRequestDto;
import br.pucminas.moeda_estudantil.dto.response.LoginResponseDto;
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

    public LoginResponseDto login(LoginRequestDto request) {
        Usuario usuario = usuarioRepository.findByEmail(request.email())
                .orElseThrow(() -> new BadCredentialsException("E-mail ou senha invalidos"));

        if (!passwordEncoder.matches(request.senha(), usuario.getSenha())) {
            throw new BadCredentialsException("E-mail ou senha invalidos");
        }

        String token = jwtTokenService.generateToken(usuario);

        return new LoginResponseDto(
                "Bearer",
                token,
                jwtTokenService.getExpirationSeconds(),
                usuario.getPerfil().name()
        );
    }
}
