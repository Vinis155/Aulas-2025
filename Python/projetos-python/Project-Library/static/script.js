

const API_URL = "/books";

// Função auxiliar para obter os headers, incluindo o token
function getAuthHeaders() {
    const authToken = localStorage.getItem('authToken');
    const userRole = localStorage.getItem('userRole');

    // Retorna os headers para a requisição
    return {
        "Content-Type": "application/json",
        // Enviamos o token (nome do usuário) para o backend verificar a ROLE
        "X-Auth-Token": authToken || "" 
    };
}

// Verifica se o botão deve ser desabilitado
function canEdit() {
    const userRole = localStorage.getItem('userRole');
    return userRole === 'admin';
}

// Adiciona um botão de Sair e checa o estado de login
function renderHeader() {
    const header = document.querySelector('.container'); // Use o container da página de livros
    let logoutButton = document.getElementById('logoutButton');
    
    if (!logoutButton) {
        logoutButton = document.createElement('button');
        logoutButton.id = 'logoutButton';
        logoutButton.textContent = 'Sair';
        logoutButton.style.cssText = 'position: absolute; top: 20px; right: 20px; padding: 8px 15px; background-color: #e74c3c; color: white;';
        logoutButton.onclick = function() {
            localStorage.removeItem('authToken');
            localStorage.removeItem('userRole');
            window.location.href = '/login';
        };
        document.body.appendChild(logoutButton);
    }

    const roleElement = document.querySelector('#userRoleDisplay');
    if (!roleElement) {
        const p = document.createElement('p');
        p.id = 'userRoleDisplay';
        p.style.cssText = 'text-align: center; margin-bottom: 20px; font-weight: bold;';
        document.querySelector('h1').insertAdjacentElement('afterend', p);
    }
    const currentRole = localStorage.getItem('userRole') || 'Visitante';
    document.querySelector('#userRoleDisplay').textContent = `Papel: ${currentRole.toUpperCase()}`;
}

async function loadBooks() {
    renderHeader();
    const res = await fetch(API_URL);
    const data = await res.json();
    const tbody = document.querySelector("#booksTable tbody");
    tbody.innerHTML = "";
    
    const isEditable = canEdit();
    document.getElementById("addForm").style.display = isEditable ? 'flex' : 'none';

    data.forEach(book => {
        const tr = document.createElement("tr");
        tr.innerHTML = `
            <td>${book.id}</td>
            <td contenteditable="${isEditable}" id="title-${book.id}">${book.title}</td>
            <td contenteditable="${isEditable}" id="author-${book.id}">${book.author}</td>
            <td class="actions-cell">
                ${isEditable ? 
                    `<button class="btn edit" onclick="updateBook(${book.id})">Salvar</button>
                    <button class="btn delete" onclick="deleteBook(${book.id})">Excluir</button>` 
                    : 'Apenas Leitura'}
            </td>`;
        tbody.appendChild(tr);
    });
}

//  getAuthHeaders() que envia o token)
async function addBook() {
    if (!canEdit()) return alert("Apenas administradores podem adicionar livros.");
    const title = document.getElementById("title").value;
    const author = document.getElementById("author").value;
    const response = await fetch(API_URL, {
        method: "POST",
        headers: getAuthHeaders(),
        body: JSON.stringify({title, author})
    });
    if (!response.ok) {
        const error = await response.json();
        alert(`Erro ao adicionar: ${error.error}`);
    } else {
        document.getElementById("title").value = "";
        document.getElementById("author").value = "";
    }
    loadBooks();
}

async function updateBook(id) {
    if (!canEdit()) return alert("Apenas administradores podem editar livros.");
    const title = document.getElementById(`title-${id}`).innerText;
    const author = document.getElementById(`author-${id}`).innerText;
    const response = await fetch(`${API_URL}/${id}`, {
        method: "PUT",
        headers: getAuthHeaders(),
        body: JSON.stringify({title, author})
    });
    if (!response.ok) {
        const error = await response.json();
        alert(`Erro ao salvar: ${error.error}`);
    }
    loadBooks();
}

async function deleteBook(id) {
    if (!canEdit()) return alert("Apenas administradores podem excluir livros.");
    const response = await fetch(`${API_URL}/${id}`, { 
        method: "DELETE",
        headers: getAuthHeaders()
    });
    if (!response.ok) {
        const error = await response.json();
        alert(`Erro ao excluir: ${error.error}`);
    }
    loadBooks();
}

loadBooks();