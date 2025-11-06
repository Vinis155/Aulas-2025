const API_URL = "/api/v1/pokemon";
let pokemons = [];
let currentPage = 0;
const perPage = 12;

async function carregarPokemons() {
    try {
        const response = await fetch(API_URL);
        pokemons = await response.json();
        exibirPokemons();
    } catch (error) {
        console.error("Erro ao carregar Pokémons:", error);
    }
}

function exibirPokemons() {
    const container = document.getElementById("pokemon-container");
    container.innerHTML = "";

    const start = currentPage * perPage;
    const end = start + perPage;
    const pagePokemons = pokemons.slice(start, end);

    pagePokemons.forEach(p => {
        const card = document.createElement("div");
        card.className = "card";
        card.innerHTML = `
            <img src="${p.imagemUrl}" alt="${p.nome}">
            <h3>${p.nome}</h3>
            <p><b>Tipo:</b> ${p.tipos}</p>
            <p><b>Fraquezas:</b> ${p.fraquezas}</p>
            <p><b>Pokédex:</b> #${p.numPokemon}</p>
            <p><b>Rota:</b> ${p.numRota}</p>
        `;
        container.appendChild(card);
    });
}

// 🔍 Busca por ID
document.getElementById("search-btn").addEventListener("click", async () => {
    const id = document.getElementById("search-id").value;
    if (!id) return;

    const response = await fetch(`${API_URL}/${id}`);
    const pokemon = await response.json();

    const container = document.getElementById("pokemon-container");
    container.innerHTML = "";

    if (pokemon && pokemon.nome) {
        const card = document.createElement("div");
        card.className = "card";
        card.innerHTML = `
            <img src="${pokemon.imagemUrl}" alt="${pokemon.nome}">
            <h3>${pokemon.nome}</h3>
            <p><b>Tipo:</b> ${pokemon.tipos}</p>
            <p><b>Fraquezas:</b> ${pokemon.fraquezas}</p>
            <p><b>Pokédex:</b> #${pokemon.numPokemon}</p>
            <p><b>Rota:</b> ${pokemon.numRota}</p>
        `;
        container.appendChild(card);
    } else {
        container.innerHTML = `<p>Pokémon não encontrado 😢</p>`;
    }
});

document.getElementById("next-btn").addEventListener("click", () => {
    if ((currentPage + 1) * perPage < pokemons.length) {
        currentPage++;
        exibirPokemons();
    }
});

document.getElementById("prev-btn").addEventListener("click", () => {
    if (currentPage > 0) {
        currentPage--;
        exibirPokemons();
    }
});

carregarPokemons();
