package br.pucminas.karv_coins.dto.request;

import br.pucminas.karv_coins.dto.EnderecoDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CriarAlunoRequestDto(

        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "A senha é obrigatória")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z\\d]).{6,}$",
                message = "A senha deve ter no mínimo 6 caracteres, com uma letra maiúscula, uma minúscula, um número e um caractere especial"
        )
        String senha,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Informe um e-mail válido")
        String email,

        @NotBlank(message = "O CPF é obrigatório")
        @Size(min = 11, max = 14, message = "CPF inválido")
        String cpf,

        @NotBlank(message = "O RG é obrigatório")
        String rg,

        @NotBlank(message = "O curso é obrigatório")
        String curso,

        @NotBlank(message = "A instituição é obrigatória")
        String instituicao,

        @NotNull(message = "O endereço é obrigatório")
        @Valid
        EnderecoDto endereco
) {}
