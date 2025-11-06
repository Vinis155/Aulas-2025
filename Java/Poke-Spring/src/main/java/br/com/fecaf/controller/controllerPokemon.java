package br.com.fecaf.controller;

import br.com.fecaf.model.Pokemon;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pokemon")
public class controllerPokemon {
    private List<Pokemon> pokemonList = new ArrayList<>();

    @PostConstruct
    public void carregarJson() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = getClass().getResourceAsStream("/data/pokemon.json");
            pokemonList = objectMapper.readValue(inputStream, new TypeReference<List<Pokemon>>() {});
            System.out.println("🔥 Json carregado com sucesso!");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar o arquivo JSON: " + e.getMessage());
        }
    }

    // Retorna todos os pokémons
    @GetMapping
    public List<Pokemon> listarPokemons() {
        return pokemonList;
    }

    // ✅ Novo endpoint: busca por ID
    @GetMapping("/{id}")
    public Pokemon buscarPorId(@PathVariable int id) {
        return pokemonList.stream()
                .filter(p -> p.getNumPokemon() == id)
                .findFirst()
                .orElse(null);
    }
}
