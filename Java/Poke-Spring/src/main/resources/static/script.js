const API_URL = "/api/v1/pokemon";
let pokemons = [];
let currentPage = 0;
const perPage = 12;

// Carrega os Pokémons
async function carregarPokemons() {
    try {
        const response = await fetch(API_URL);
        pokemons = await response.json();
        console.log("Pokémons carregados:", pokemons); // 👈 veja no console se vem os dados
        exibirPokemons();
    } catch (error) {
        console.error("Erro ao carregar Pokémons:", error);
    }
}
// 🎨 Mapa de cores por tipo
const tipoCores = {
    "Fogo": "#ff6b6b",
    "Água": "#4dabf7",
    "Grama": "#51cf66",
    "Elétrico": "#ffd43b",
    "Gelo": "#74c0fc",
    "Lutador": "#e8590c",
    "Voador": "#a5d8ff",
    "Psíquico": "#f783ac",
    "Inseto": "#94d82d",
    "Pedra": "#adb5bd",
    "Terra": "#d8a657",
    "Veneno": "#b197fc",
    "Fantasma": "#868e96",
    "Normal": "#ced4da",
    "Dragão": "#5c7cfa",
    "Fada": "#fcc2d7",
    "Aço": "#dee2e6",
    "Sombrio": "#495057"
};

function getCorTipo(tipo) {
    for (const key in tipoCores) {
        if (tipo.includes(key)) {
            return tipoCores[key];
        }
    }
    return "#ccc"; // cor padrão
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

        // Divide tipos e fraquezas
        const tiposArray = p.tipos.split("/");
        const fraquezasArray = p.fraquezas.split(",");

        // Cores dos tipos (cada um colorido)
        const tiposColoridos = tiposArray.map(tipo => {
            const cor = getCorTipo(tipo.trim());
            return `<span style="color:${cor}; font-weight:bold;">${tipo.trim()}</span>`;
        }).join(" / ");

        // Cores das fraquezas (sem exagerar)
        const fraquezasColoridas = fraquezasArray.map(f => {
            const cor = getCorTipo(f.trim());
            return `<span style="color:${cor};">${f.trim()}</span>`;
        }).join(", ");

        // Cor principal do card
        const corPrincipal = getCorTipo(tiposArray[0].trim());
        const corSecundaria = tiposArray[1] ? getCorTipo(tiposArray[1].trim()) : corPrincipal;

        // Estrutura do card
        card.innerHTML = `
            <img src="${p.imagemUrl}" alt="${p.nome}">
            <h3>${p.nome}</h3>
            <p><b>Tipo:</b> ${tiposColoridos}</p>
            <p><b>Pokédex:</b> #${p.numPokemon}</p>
            <p><b>Rota:</b> ${p.numRota}</p>
            <p><b>Fraquezas:</b> ${fraquezasColoridas}</p>
        `;

        // Fundo com gradiente se tiver dois tipos
        card.style.background = tiposArray[1]
            ? `linear-gradient(135deg, ${corPrincipal}22, ${corSecundaria}22)`
            : `${corPrincipal}11`;
        card.style.border = `2px solid ${corPrincipal}`;
        card.style.boxShadow = `0 0 15px ${corPrincipal}66`;

        container.appendChild(card);
    });
}





// Botão Próximo
document.getElementById("next-btn").addEventListener("click", () => {
    if ((currentPage + 1) * perPage < pokemons.length) {
        currentPage++;
        exibirPokemons();
    }
});

// Botão Anterior
document.getElementById("prev-btn").addEventListener("click", () => {
    if (currentPage > 0) {
        currentPage--;
        exibirPokemons();
    }
});

// Inicializa
carregarPokemons();
