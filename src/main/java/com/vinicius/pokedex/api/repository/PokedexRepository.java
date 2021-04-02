package com.vinicius.pokedex.api.repository;

import com.vinicius.pokedex.api.domain.model.Pokemon;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface PokedexRepository extends ReactiveMongoRepository<Pokemon, String> {
}
