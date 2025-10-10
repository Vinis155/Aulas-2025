const POKEMON_CONTAINER = document.getElementById('pokemon-container');
const API_URL = 'https://pokeapi.co/api/v2/pokemon?limit=30';

// Função auxiliar para buscar dados de um URL e retornar JSON
async function fetchData(url) {
    const response = await fetch(url);
    if (!response.ok) {
        console.error(`Erro na requisição para ${url}: ${response.statusText}`);
        return null;
    }
    return await response.json();
}

// Função para buscar DETALHES e a DESCRIÇÃO (Species) do Pokémon
async function getPokemonDetails(url) {
    // 1. Busca os detalhes principais (imagem, tipos, ID)
    const details = await fetchData(url);
    if (!details) return null;

    // 2. Busca o endpoint de Species para obter a descrição/história
    const species = await fetchData(details.species.url);

    // 3. Formata a descrição, priorizando Português ('pt')
    let descriptionText = 'Descrição não encontrada.';
    if (species && species.flavor_text_entries) {

        // Prioriza o texto em Português
        const entry_pt = species.flavor_text_entries.find(
            (entry) => entry.language.name === 'pt'
        );

        // Alternativa: Se o Português falhar, usa o Inglês
        const entry_en = species.flavor_text_entries.find(
            (entry) => entry.language.name === 'en'
        );

        // Usa Português, se disponível. Senão, usa Inglês.
        const final_entry = entry_pt || entry_en;

        if (final_entry) {
            // Remove quebras de linha e formata
            descriptionText = final_entry.flavor_text.replace(/[\n\f]/g, ' ');
        }
    }

    // Retorna todos os dados úteis
    return {
        ...details,
        description: descriptionText
    };
}

// Função para criar e inserir o card no DOM (Criação Dinâmica)
function createPokemonCard(pokemon) {
    const card = document.createElement('div');
    card.classList.add('pokemon-card');

    // Nome, Imagem e ID
    const id = document.createElement('span');
    id.classList.add('pokemon-id');
    id.textContent = `#${pokemon.id.toString().padStart(3, '0')}`;

    const image = document.createElement('img');
    image.src = pokemon.sprites.front_default;
    image.alt = `Imagem de ${pokemon.name}`;

    const name = document.createElement('h2');
    name.textContent = pokemon.name;

    // Descrição
    const description = document.createElement('p');
    description.classList.add('pokemon-description');
    description.textContent = pokemon.description;

    // Tipos (Status ou Descrição Adicional)
    const typesContainer = document.createElement('p');
    typesContainer.classList.add('pokemon-types');

    pokemon.types.forEach(typeInfo => {
        const typeSpan = document.createElement('span');
        const typeName = typeInfo.type.name; // Pega o nome do tipo (ex: fire)

        typeSpan.textContent = typeName;

        // ADICIONA A CLASSE PARA A COR TEMÁTICA
        typeSpan.classList.add(`type-${typeName}`);

        typesContainer.appendChild(typeSpan);
    });

    // Anexando todos os elementos ao card
    card.appendChild(id);
    card.appendChild(image);
    card.appendChild(name);
    card.appendChild(description);
    card.appendChild(typesContainer);

    POKEMON_CONTAINER.appendChild(card);
}

// Função principal para consumir a API
async function fetchPokemonData() {
    POKEMON_CONTAINER.innerHTML = '<h2>Carregando Pokémon...</h2>';

    try {
        const responseList = await fetchData(API_URL);
        if (!responseList) throw new Error("Falha ao obter a lista inicial.");

        const pokemonPromises = responseList.results.map(pokemon =>
            getPokemonDetails(pokemon.url)
        );

        const detailedPokemon = await Promise.all(pokemonPromises);

        // Limpa o container e insere os cards
        POKEMON_CONTAINER.innerHTML = '';

        detailedPokemon
            .filter(pokemon => pokemon !== null)
            .forEach(createPokemonCard);

    } catch (error) {
        console.error("Erro fatal ao carregar Pokémon:", error);
        POKEMON_CONTAINER.innerHTML = '<h2>Falha ao carregar dados da PokéAPI. Verifique sua conexão e o console.</h2>';
    }
}

// Inicia a aplicação
fetchPokemonData();