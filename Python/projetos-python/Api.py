from flask import Flask, Response, request, render_template
from flask_cors import CORS
import json

app = Flask(__name__)

# Garante que o JSON será ASCII (acentos, emojis etc.)
app.config["JSON_AS_ASCII"] = False

# Configurando CORS
CORS(app)

def json_response(payload, status=200):
    return Response(
        json.dumps(payload, ensure_ascii=False),
        status=status,
        mimetype="application/json; charset=utf-8"
    )

# "Banco de dados" em memória
filmes = [
    {"id": 1, "name": "Vingadores", "diretor": "Joss Whedon", "genero": "Ação"},
    {"id": 2, "name": "Corra", "diretor": "Jordan Peele", "genero": "Terror"},
    {"id": 3, "name": "Interestelar", "diretor": "Christopher Nolan", "genero": "Ficção Científica"},
    {"id": 4, "name": "O Poderoso Chefão", "diretor": "Francis Ford Coppola", "genero": "Drama"},
    {"id": 5, "name": "Matrix", "diretor": "Lana e Lilly Wachowski", "genero": "Ficção Científica"}
]

# ✅ Rota para servir o HTML
@app.route("/")
def index():
    return render_template("index.html")

# GET - listar todos os filmes
@app.route("/filmes", methods=["GET"])
def get_filmes():
    return json_response(filmes)

# GET - buscar um filme pelo id
@app.route("/filmes/<int:filme_id>", methods=["GET"])
def get_filme(filme_id):
    for f in filmes:
        if f["id"] == filme_id:
            return json_response(f)
    return json_response({"error": "Filme não encontrado"}, status=404)

#---------------------------------------------------------------------

# POST - criar um novo filme
@app.route("/filmes", methods=["POST"])
def create_filme():
    data = request.get_json(silent=True) or {}
    filmes.append(data)
    return json_response(data, status=201)  # 201 Created

# PUT - atualizar um filme existente
@app.route("/filmes/<int:filme_id>", methods=["PUT"])
def update_filme(filme_id):  
    data = request.get_json(silent=True) or {}
    for filme in filmes:
        if filme["id"] == filme_id:
            filme.update(data)
            return json_response(filme)
    return json_response({"error": "Filme não encontrado"}, status=404)

# DELETE - remover filme
@app.route("/filmes/<int:filme_id>", methods=["DELETE"])
def delete_filme(filme_id):
    global filmes
    before = len(filmes)
    filmes = [u for u in filmes if u["id"] != filme_id]
    if len(filmes) == before:
        return json_response({"error": "Filme não encontrado"}, status=404)
    return json_response({"msg": f"Filme {filme_id} removido"})

if __name__ == "__main__":
    app.run(debug=True, port=3344)
