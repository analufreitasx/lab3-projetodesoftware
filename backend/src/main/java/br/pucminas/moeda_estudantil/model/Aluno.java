import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Serdeable
@Entity
@Table(name = "aluno")
@Data
@EqualsAndHashCode(callSuper = true)
public class Aluno extends Usuario {
    @Column(unique = true)
    private String rg;

    @Column(unique = true)
    private String cpf;

    private String profissao;

    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private Double saldo;
    private String instituição // Alterar tipo para instituiçao pos implementação
    private String curso // Alterar tipo para curso pos implementação
}