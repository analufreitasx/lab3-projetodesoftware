package br.pucminas.moeda_estudantil.controller;

import br.pucminas.moeda_estudantil.dto.request.LoginRequestDto;
import br.pucminas.moeda_estudantil.dto.response.AccessValidationResponseDto;
import br.pucminas.moeda_estudantil.dto.response.LoginResponseDto;
import br.pucminas.moeda_estudantil.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/validate")
    public ResponseEntity<AccessValidationResponseDto> validateAccess(Jwt jwt) {
        String perfil = jwt.getClaimAsString("perfil");
        return ResponseEntity.ok(new AccessValidationResponseDto(true, jwt.getSubject(), perfil));
    }
}
