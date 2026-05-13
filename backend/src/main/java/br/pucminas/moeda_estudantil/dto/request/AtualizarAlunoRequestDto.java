package br.pucminas.moeda_estudantil.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record AtualizarAlunoRequestDto(

        String nome,

        @Email(message = "Informe um e-mail válido")
        String email,

        @Size(min = 6, message = "A nova senha deve ter no mínimo 6 caracteres")
        String senha,

        String curso,

        String instituicao
) {}
