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
titulos = [
    {"id": 1, "name": "Vingadores"},
    {"id": 2, "name": "Corra"}
    
]


# ✅ Rota para servir o HTML
@app.route("/")
def index():
    return render_template("index.html")


# GET - listar todos os títulos
@app.route("/titulos", methods=["GET"])
def get_titulos():
    return json_response(titulos)

# GET - buscar um título pelo id
@app.route("/titulos/<int:titulo_id>", methods=["GET"])
def get_titulo(titulo_id):
    # titulo = next((t for t in titulos if t["id"] == titulo_id), None)

    for t in titulos:
        if t["id"] == titulo_id:
            return json_response(t)
        
    return json_response({"error": "Título não encontrado"}, status=404)











#---------------------------------------------------------------------

# POST - criar um novo título
@app.route("/titulos", methods=["POST"])
def create_titulo():
    data = request.get_json(silent=True) or {}
    titulos.append(data)
    return json_response(data, status=201)  # 201 Created

# PUT - atualizar um título existente
@app.route("/titulos/<int:titulo_id>", methods=["PUT"])
def update_titulo(titulo_id):  
    data = request.get_json(silent=True) or {}
    for titulo in titulos:
        if titulo["id"] == titulo_id:
            titulo.update(data)
            return json_response(titulo)
    return json_response({"error": "Título não encontrado"}, status=404)

# DELETE - remover título
@app.route("/titulos/<int:titulo_id>", methods=["DELETE"])
def delete_titulo(titulo_id):
    global titulos
    before = len(titulos)
    titulos = [u for u in titulos if u["id"] != titulo_id]
    if len(titulos) == before:
        return json_response({"error": "Título não encontrado"}, status=404)
    return json_response({"msg": f"Título {titulo_id} removido"})

if __name__ == "__main__":
    app.run(debug=True, port=3344)
