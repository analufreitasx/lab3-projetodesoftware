package br.pucminas.karv_coins.dto;

import br.pucminas.karv_coins.model.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EnderecoDto(

        @Size(max = 9, message = "CEP inválido")
        String cep,

        @NotBlank(message = "A rua é obrigatória")
        String rua,

        @NotBlank(message = "O número é obrigatório")
        String numero,

        String complemento,

        @NotBlank(message = "O bairro é obrigatório")
        String bairro,

        @NotBlank(message = "A cidade é obrigatória")
        String cidade,

        @NotBlank(message = "A UF é obrigatória")
        @Size(min = 2, max = 2, message = "UF deve ter 2 caracteres")
        String uf
) {
    public Endereco toEntity() {
        return new Endereco(cep, rua, numero, complemento, bairro, cidade, uf);
    }

    public static EnderecoDto from(Endereco endereco) {
        if (endereco == null) {
            return null;
        }
        return new EnderecoDto(
                endereco.getCep(),
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getUf()
        );
    }
}
