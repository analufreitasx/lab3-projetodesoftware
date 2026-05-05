package br.pucminas.moeda_estudantil.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "professor")
@Data
@EqualsAndHashCode(callSuper = true)
public class Professor extends Usuario {
    private String departamento;
}
