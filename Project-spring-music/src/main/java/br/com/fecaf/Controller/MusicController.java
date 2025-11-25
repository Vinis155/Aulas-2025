package br.com.fecaf.Controller;

import br.com.fecaf.Model.Music;
import br.com.fecaf.Model.MusicRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/musics")
@CrossOrigin(origins = "*")
public class MusicController {

        private final MusicRepository repository;

        public MusicController(MusicRepository repository) {
            this.repository = repository;
        }

        // 🔄 Carrega o Musics.json e insere no banco ao iniciar o app
        @PostConstruct
        public void init() {
            try {
                if (repository.count() == 0) { // evita duplicar caso já existam registros
                    ObjectMapper mapper = new ObjectMapper();
                    InputStream inputStream = getClass().getResourceAsStream("/Musics.json");
                    List<Music> musicas = mapper.readValue(inputStream, new TypeReference<List<Music>>() {});
                    repository.saveAll(musicas);
                    System.out.println("🎵 Dados do Musics.json importados com sucesso!");
                }
            } catch (Exception e) {
                System.err.println("❌ Erro ao carregar Musics.json: " + e.getMessage());
            }
        }

        @GetMapping
        public List<Music> listarMusicas() {
            return repository.findAll();
        }

        @GetMapping("/{id}")
        public Optional<Music> buscarMusica(@PathVariable Long id) {
            return repository.findById(id);
        }

        @PostMapping
        public Music adicionarMusica(@RequestBody Music novaMusica) {
            return repository.save(novaMusica);
        }

        @PutMapping("/{id}")
        public Music atualizarMusica(@PathVariable Long id, @RequestBody Music musicaAtualizada) {
            return repository.findById(id)
                    .map(musica -> {
                        musica.setNomemusic(musicaAtualizada.getNomemusic());
                        musica.setArtista(musicaAtualizada.getArtista());
                        musica.setAno(musicaAtualizada.getAno());
                        musica.setDuracao(musicaAtualizada.getDuracao());
                        musica.setGenre(musicaAtualizada.getGenre());
                        return repository.save(musica);
                    }).orElseGet(() -> {
                        musicaAtualizada.setId(id);
                        return repository.save(musicaAtualizada);
                    });
        }

        @DeleteMapping("/{id}")
        public void deletarMusica(@PathVariable Long id) {
            repository.deleteById(id);
        }
    }
