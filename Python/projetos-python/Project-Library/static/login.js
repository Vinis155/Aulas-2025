
document.getElementById('loginForm').addEventListener('submit', async function(event) {
    event.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const loginMessage = document.getElementById('loginMessage');
    loginMessage.textContent = ''; // Limpa mensagens anteriores

    try {
        const response = await fetch('/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ username, password })
        });

        const data = await response.json();

        if (response.ok) {
            // Sucesso no Login. Armazena o 'token' e o 'role' no navegador.
            localStorage.setItem('authToken', data.token); // O token é o nome de usuário
            localStorage.setItem('userRole', data.role); // O papel é 'admin' ou 'standard'
            
            // Redireciona para a página de livros
            window.location.href = '/livros';
        } else {
            // Falha no Login
            loginMessage.textContent = data.error || 'Erro ao tentar fazer login.';
        }
    } catch (error) {
        loginMessage.textContent = 'Erro de conexão com o servidor.';
        console.error('Erro de rede:', error);
    }
});