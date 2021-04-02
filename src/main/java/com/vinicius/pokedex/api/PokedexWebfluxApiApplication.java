package com.vinicius.pokedex.api;

import com.vinicius.pokedex.api.domain.model.Pokemon;
import com.vinicius.pokedex.api.domain.repository.PokedexRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class PokedexWebfluxApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PokedexWebfluxApiApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ReactiveMongoOperations operations, PokedexRepository pokedexRepository) {
        return args -> {
            Flux<Pokemon> pokemonFlux = Flux.just(
                    new Pokemon(null, "Bulbassauro", "Semente", "OverGrow", 6.09),
                    new Pokemon(null, "Charizard", "Fogo", "Blaze", 90.05),
                    new Pokemon(null, "Caterpie", "Minhoca", "Poeira do Escudo", 2.09),
                    new Pokemon(null, "Blastoise", "Marisco", "Torrente", 6.09))
                    .flatMap(pokedexRepository::save);

            pokemonFlux
                    .thenMany(pokedexRepository.findAll())
                    .subscribe(System.out::println);
        };
    }
}
