package com.vinicius.pokedex.api.controller;

import com.vinicius.pokedex.api.domain.model.Pokemon;
import com.vinicius.pokedex.api.domain.model.PokemonEvent;
import com.vinicius.pokedex.api.domain.repository.PokedexRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {

    private final PokedexRepository pokedexRepository;

    public PokemonController(PokedexRepository pokedexRepository) {
        this.pokedexRepository = pokedexRepository;
    }

    @GetMapping
    public Flux<Pokemon> getAll() {
        return pokedexRepository.findAll();
    }

    @GetMapping("/{codigo}")
    public Mono<ResponseEntity<Pokemon>> getPokemon(@PathVariable String codigo) {
        return pokedexRepository.findById(codigo)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/events", produces = TEXT_EVENT_STREAM_VALUE)
    public Flux<PokemonEvent> getPokemonEvents() {
        return Flux.interval(Duration.ofSeconds(5))
                .map(val -> new PokemonEvent(val, "Evento de Pokemon"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Pokemon> savePokemon(@RequestBody Pokemon pokemon) {
        return pokedexRepository.save(pokemon);
    }

}
