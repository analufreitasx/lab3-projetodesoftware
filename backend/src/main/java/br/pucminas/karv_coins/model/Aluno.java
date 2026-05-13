package br.pucminas.karv_coins.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "alunos")
@Data
@EqualsAndHashCode(callSuper = true)
public class Aluno extends Usuario {
    @Column(unique = true)
    private String rg;

    @Column(unique = true)
    private String cpf;

    private String curso;
    private String instituicao;
    private String endereco;
    private Double saldo = 0.0;
}