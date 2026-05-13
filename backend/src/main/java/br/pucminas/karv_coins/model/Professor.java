package br.pucminas.karv_coins.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "professores")
@Data
@EqualsAndHashCode(callSuper = true)
public class Professor extends Usuario {
    private String departamento;
}
