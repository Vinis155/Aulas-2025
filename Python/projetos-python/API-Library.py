from flask import Flask, Response, request, render_template
from flask_cors import CORS
import json

app = Flask(__name__)
app.config["JSON_AS_ASCII"] = False
CORS(app)

def json_response(payload, status=200):
    return Response(
        json.dumps(payload, ensure_ascii=False),
        status=status,
        mimetype="application/json; charset=utf-8"
    )


# "Banco de dados" - lista de livros

books = [
    {"id": 1, "title": "1984", "author": "George Orwell"},
    {"id": 2, "title": "O Pequeno Príncipe", "author": "Antoine de Saint-Exupéry"}
]

# Página inicial 
@app.route("/")
def index():
    return "<h1>Bem-vindo à API de Livros</h1>"

# Listar todos os livros (GET)
@app.route("/books", methods=["GET"])
def get_books():
    return json_response(books)

# Buscar livro por id (GET)
@app.route("/books/<int:book_id>", methods=["GET"])
def get_book(book_id):
    for book in books:
        if book["id"] == book_id:
            return json_response(book)
    return json_response({"error": "Livro não encontrado"}, status=404)

# Criar novo livro (POST)
@app.route("/books", methods=["POST"])
def create_book():
    data = request.get_json(silent=True) or {}
    # Gera novo id simples para o novo livro
    new_id = max(book["id"] for book in books) + 1 if books else 1
    new_book = {
        "id": new_id,
        "title": data.get("title", ""),
        "author": data.get("author", "")
    }
    books.append(new_book)
    return json_response(new_book, status=201)

# Atualizar livro existente (PUT)
@app.route("/books/<int:book_id>", methods=["PUT"])
def update_book(book_id):
    data = request.get_json(silent=True) or {}
    for book in books:
        if book["id"] == book_id:
            book["title"] = data.get("title", book["title"])
            book["author"] = data.get("author", book["author"])
            return json_response(book)
    return json_response({"error": "Livro não encontrado"}, status=404)

# Remover livro (DELETE)
@app.route("/books/<int:book_id>", methods=["DELETE"])
def delete_book(book_id):
    global books
    before_len = len(books)
    books = [b for b in books if b["id"] != book_id]
    if len(books) == before_len:
        return json_response({"error": "Livro não encontrado"}, status=404)
    return json_response({"msg": f"Livro {book_id} removido"})

if __name__ == "__main__":
    app.run(debug=True, port=3344)
