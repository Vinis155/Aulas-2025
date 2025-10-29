from flask import Flask, Response, request, render_template
from flask_cors import CORS
import json, os
# NOVO: Importe wraps para corrigir o erro de endpoint
from functools import wraps 
# ... o restante das suas importações

# Caminho base do projeto
# Mantenha o seu caminho original aqui, ou apenas o use "os.path.dirname(os.path.abspath(__file__))"
BASE_DIR = r"C:\Users\vinis\Desktop\Aulas-2025\Python\projetos-python\Project-Library" 

app = Flask(
    __name__,
    static_folder=os.path.join(BASE_DIR, "static"),
    template_folder=os.path.join(BASE_DIR, "templates")
)
app.config["JSON_AS_ASCII"] = False
CORS(app)

def json_response(payload, status=200):
    return Response(
        json.dumps(payload, ensure_ascii=False),
        status=status,
        mimetype="application/json; charset=utf-8"
    )

# -------------------------------
# "Banco de dados" em memória (Livros e Usuários)
# -------------------------------
books = [
    {"id": 1, "title": "1984", "author": "George Orwell"},
    {"id": 2, "title": "O Pequeno Príncipe", "author": "Antoine de Saint-Exupéry"}
]

# NOVO: Usuários em memória (Token/ID será o username)
users = {
    "admin": {"password": "adminpassword", "role": "admin"},
    "user": {"password": "userpassword", "role": "standard"},
}

# NOVO: Decorator para verificar se o usuário é Admin
def admin_required(f):
    @wraps(f) # <-- ESSA LINHA CORRIGE O ERRO!
    def decorated_function(*args, **kwargs):
        # O nome do usuário (o nosso "token") deve ser enviado no header 'X-Auth-Token'
        auth_token = request.headers.get("X-Auth-Token")
        
        # 1. Verifica se o token (usuário) foi fornecido e existe
        if not auth_token or auth_token not in users:
            # Não Autorizado
            return json_response({"error": "Autorização requerida: Faça login e envie o token."}, status=401)
        
        # 2. Verifica o papel (role) do usuário
        user_role = users[auth_token]["role"]
        
        if user_role != "admin":
            # Proibido
            return json_response({"error": "Permissão negada: Você não é um administrador."}, status=403)
            
        # Se for admin, executa a função original
        return f(*args, **kwargs)
    return decorated_function


@app.route("/")
def index():
    return render_template("index.html")

@app.route("/livros")
def livros_page():
    return render_template("livros.html")


@app.route("/login")
def login_page():
    return render_template("login.html")


@app.route("/auth/login", methods=["POST"])
def auth_login():
    data = request.get_json(silent=True) or {}
    username = data.get("username")
    password = data.get("password")

    if username in users and users[username]["password"] == password:
        # Login bem-sucedido: retorna o nome do usuário (Token) e o papel (Role)
        role = users[username]["role"]
        return json_response({"token": username, "role": role}, status=200)
    else:
        # Falha no login
        return json_response({"error": "Usuário ou senha incorretos"}, status=401)


@app.route("/books", methods=["GET"])
def get_books():
    return json_response(books)

@app.route("/books/<int:book_id>", methods=["GET"])
def get_book(book_id):
    for book in books:
        if book["id"] == book_id:
            return json_response(book)
    return json_response({"error": "Livro não encontrado"}, status=404)

# Os endpoints de modificação (POST, PUT, DELETE) agora são protegidos:
@app.route("/books", methods=["POST"])
@admin_required # <-- APENAS ADMIN PODE
def create_book():
    data = request.get_json(silent=True) or {}
    new_id = max(book["id"] for book in books) + 1 if books else 1
    new_book = {"id": new_id, "title": data.get("title", ""), "author": data.get("author", "")}
    books.append(new_book)
    return json_response(new_book, status=201)

@app.route("/books/<int:book_id>", methods=["PUT"])
@admin_required # <-- APENAS ADMIN PODE
def update_book(book_id):
    data = request.get_json(silent=True) or {}
    for book in books:
        if book["id"] == book_id:
            book["title"] = data.get("title", book["title"])
            book["author"] = data.get("author", book["author"])
            return json_response(book)
    return json_response({"error": "Livro não encontrado"}, status=404)

@app.route("/books/<int:book_id>", methods=["DELETE"])
@admin_required # <-- APENAS ADMIN PODE
def delete_book(book_id):
    global books
    before_len = len(books)
    books = [b for b in books if b["id"] != book_id]
    if len(books) == before_len:
        return json_response({"error": "Livro não encontrado"}, status=404)
    return json_response({"msg": f"Livro {book_id} removido"})

if __name__ == "__main__":
    app.run(debug=True, port=3344)