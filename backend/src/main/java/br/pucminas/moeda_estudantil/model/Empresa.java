package br.pucminas.moeda_estudantil.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "empresa")
@Data
@EqualsAndHashCode(callSuper = true)
public class Empresa extends Usuario {
    private String cnpj;
}
