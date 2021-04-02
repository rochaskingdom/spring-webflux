package com.vinicius.pokedex.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PokemonEvent {

    private Long eventCodigo;
    private String eventType;
}
