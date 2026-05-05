package br.pucminas.moeda_estudantil.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "aluno")
@Data
@EqualsAndHashCode(callSuper = true)
public class Aluno extends Usuario {
    @Column(unique = true)
    private String rg;

    @Column(unique = true)
    private String cpf;

    private String curso;
    private String instituicao;
}