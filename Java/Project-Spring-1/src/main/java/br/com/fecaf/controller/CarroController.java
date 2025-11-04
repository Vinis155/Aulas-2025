package br.com.fecaf.controller;

import br.com.fecaf.model.Carro;
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
@RequestMapping("/api/v1/carros")
public class CarroController {
    private List<Carro> carrosList = new ArrayList<>();

    @PostConstruct
    public void carregarJson() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // ✅ Caminho corrigido
            InputStream inputStream = getClass().getResourceAsStream("/data/data.json");

            if (inputStream == null) {
                throw new RuntimeException("❌ Arquivo data.json não encontrado em /resources/data/");
            }

            carrosList = objectMapper.readValue(inputStream, new TypeReference<List<Carro>>() {});
            System.out.println("✅ JSON carregado com sucesso! Carros: " + carrosList.size());

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao carregar o arquivo JSON: " + e.getMessage());
        }
    }

    @GetMapping
    public List<Carro> listarCarros() {
        return carrosList;
    }
}
