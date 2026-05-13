package br.pucminas.karv_coins.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "empresas")
@Data
@EqualsAndHashCode(callSuper = true)
public class Empresa extends Usuario {
    private String cnpj;
}
