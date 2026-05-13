package br.pucminas.karv_coins.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CriarEmpresaRequestDto(

        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
        String senha,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Informe um e-mail válido")
        String email,

        @NotBlank(message = "O CNPJ é obrigatório")
        @Size(min = 14, max = 18, message = "CNPJ inválido")
        String cnpj
) {}
