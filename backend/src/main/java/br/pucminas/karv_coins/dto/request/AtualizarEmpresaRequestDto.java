package br.pucminas.karv_coins.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record AtualizarEmpresaRequestDto(

        String nome,

        @Email(message = "Informe um e-mail válido")
        String email,

        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z\\d]).{6,}$",
                message = "A nova senha deve ter no mínimo 6 caracteres, com uma letra maiúscula, uma minúscula, um número e um caractere especial"
        )
        String senha
) {}
