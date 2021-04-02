package com.vinicius.pokedex.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pokemon {

    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    private String nome;
    private String categoria;
    private String habilidade;
    private Double peso;

}
