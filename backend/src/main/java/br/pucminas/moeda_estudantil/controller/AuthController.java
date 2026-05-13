package br.pucminas.moeda_estudantil.controller;

import br.pucminas.moeda_estudantil.dto.request.LoginRequestDto;
import br.pucminas.moeda_estudantil.dto.response.AccessValidationResponseDto;
import br.pucminas.moeda_estudantil.dto.response.LoginResponseDto;
import br.pucminas.moeda_estudantil.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Autenticação", description = "Login e validação de tokens JWT")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @SecurityRequirements
    @Operation(
            summary = "Autenticar usuário",
            description = "Recebe e-mail e senha e devolve o token JWT junto com o perfil do usuário."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login bem-sucedido"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/validate")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(
            summary = "Validar token JWT",
            description = "Confere se o token enviado no header Authorization é válido e devolve o perfil."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Token válido"),
            @ApiResponse(responseCode = "401", description = "Token ausente ou inválido")
    })
    public ResponseEntity<AccessValidationResponseDto> validateAccess(Jwt jwt) {
        String perfil = jwt.getClaimAsString("perfil");
        return ResponseEntity.ok(new AccessValidationResponseDto(true, jwt.getSubject(), perfil));
    }
}
