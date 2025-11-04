package br.com.fecaf.controller;

import br.com.fecaf.model.Pokemon;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pokemon")
public class controllerPokemon {

    private List<Pokemon> pokemons = new ArrayList<>();

    @PostConstruct
    public void carregarJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = getClass().getResourceAsStream("/data/pokemon.json");

            pokemons = mapper.readValue(inputStream, new TypeReference<List<Pokemon>>() {});
            System.out.println("✅ JSON de Pokémon carregado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping
    public List<Pokemon> listar() {
        return pokemons;
    }
}
