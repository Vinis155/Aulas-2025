
#Realiza as importações
from flask import Flask, Response, request, render_template
from flask_cors import CORS
import json, os
# NOVO: Importe wraps para corrigir o erro de endpoint
from functools import wraps 
# ... o restante das suas importações

# Caminho base do projeto
BASE_DIR = r"C:\Users\vinis\Desktop\Aulas-2025\Python\projetos-python\Project-Library" 

#Aqui estou criando um caminho para procurar arquivos estaticos e os templates
app = Flask(
    __name__,
    static_folder=os.path.join(BASE_DIR, "static"),
    template_folder=os.path.join(BASE_DIR, "templates")
)

#Esta permitindo que caracteres especiais sejam enviados em Json
app.config["JSON_AS_ASCII"] = False

#Habilita que o front faça requisição para o flask sem ser parado
CORS(app)

#Garante que as informações do front sejam formatadas em Json
def json_response(payload, status=200):
    return Response(
        json.dumps(payload, ensure_ascii=False),
        status=status,
        mimetype="application/json; charset=utf-8"
    )

# -------------------------------
# "Banco de dados" em memória (Livros e Usuários)
# -------------------------------

#Nome da tabela e seus atributos
books = [
    {"id": 1, "title": "1984", "author": "George Orwell"},
    {"id": 2, "title": "O Pequeno Príncipe", "author": "Antoine de Saint-Exupéry"}
]

#Usuários em memória (Token/ID será o username) aqui define os "papeis" de cada usuario
users = {
    "admin": {"password": "adminpassword", "role": "admin"},
    "user": {"password": "userpassword", "role": "standard"},
}

 # Isso é um decorador que vai envolver outra função que vai ser atribuido a "f"
def admin_required(f):
    #Esse comando garante que a função decorada herde nome,atributo da função original que esta no "f"
    @wraps(f)

    #Seria a portaria do sistema, o args e o kwargs receber qualquer tipo de argumento que a função "f" possa receber
    def decorated_function(*args, **kwargs):
        #Ele pega o token que é o nome do usuario como se fosse a credencial e após isso o backend valida as autorizações
        auth_token = request.headers.get("X-Auth-Token")
        

        # 1. Verifica se o token (usuário) foi fornecido e existe
        if not auth_token or auth_token not in users:

            # Não Autorizado
            return json_response({"error": "Autorização requerida: Faça login e envie o token."}, status=401)
        
        # 2. Verifica o papel (role) do usuário
        user_role = users[auth_token]["role"]
        
        #Verifica se é diferente de role admin
        if user_role != "admin":
            # Proibido
            return json_response({"error": "Permissão negada: Você não é um administrador."}, status=403)
            
        # Se for admin, executa a função original
        return f(*args, **kwargs)
    return decorated_function


#Quando o usuário acessa http://127.0.0.1:3344/, o Flask executa a função index() 
# e envia para o index.html
@app.route("/")
def index():
    return render_template("index.html")

#O Flask envia o conteúdo do arquivo livros.html.
#O JavaScript dessa página é então executado no navegador para carregar os dados da API.
@app.route("/livros")
def livros_page():
    return render_template("livros.html")

#O Flask envia o conteúdo do arquivo login.html. 
# O JavaScript de login dessa página é responsável por enviar as credenciais para a próxima rota 
# (/auth/login).
@app.route("/login")
def login_page():
    return render_template("login.html")

#Aqui mostra o caminho e informa que só aceita o metodo post
@app.route("/auth/login", methods=["POST"])
def auth_login():
    data = request.get_json(silent=True) or {}
    username = data.get("username") #prepara para fazer a verificação
    password = data.get("password")#prepara para fazer a verificação

    if username in users and users[username]["password"] == password:
        # Login bem-sucedido: retorna o nome do usuário (Token) e o papel (Role)
        role = users[username]["role"]
        return json_response({"token": username, "role": role}, status=200)
    else:
        # Falha no login
        return json_response({"error": "Usuário ou senha incorretos"}, status=401)

#Retorna a lista de todos os livros
@app.route("/books", methods=["GET"])
def get_books():
    return json_response(books)

#Realiza uma busca por id e caso não encontre informa o erro
@app.route("/books/<int:book_id>", methods=["GET"])
def get_book(book_id):
    for book in books: #Realiza uma iteração na lista de livros
        if book["id"] == book_id: #Compara e verifica o ID do livro
            return json_response(book) #Faz o retorno do livro
    return json_response({"error": "Livro não encontrado"}, status=404)

#Só vai ser executada quando receber uma requisição, após receber envia os dados para criar o Livro 
@app.route("/books", methods=["POST"])
@admin_required # Apenas o Admin pode

def create_book():
    #Envia o nome do livro e do autor em formato Json e o flask usa o request para ler os dados.
    data = request.get_json(silent=True) or {}
    #Garante que sempre o novo ID vai ser sequencial e não repetido.
    new_id = max(book["id"] for book in books) + 1 if books else 1
    #Cria um novo dicionaria contando com o novo id que foi criado e pega os dados de livro e autor
    new_book = {"id": new_id, "title": data.get("title", ""), "author": data.get("author", "")}
    #Adiciona no banco o novo livro
    books.append(new_book)
    return json_response(new_book, status=201)


#Informa que a requisição vai ser do tipo PUT'
@app.route("/books/<int:book_id>", methods=["PUT"])
@admin_required # apenas usuario do tipo role admin pode executar 
def update_book(book_id): # A função recebe o book_id da URL como argumento.
    data = request.get_json(silent=True) or {} #Aqui esta recebendo as novas informações que 
    #vão ser atualizadas
    
    #Verifica o ID correspondente ao ID que precisa ser atualizado
    for book in books:
        if book["id"] == book_id: #Em cada iteração verifica se o ID é correspondente que veio da URL
            #O .get("title") tenta buscar o novo título dentro do dicionário data (os dados enviados pelo frontend).
            book["title"] = data.get("title", book["title"])
            book["author"] = data.get("author", book["author"])
            return json_response(book) #Se for encontrado e atualizado retorna atualizado com o status padrão
    return json_response({"error": "Livro não encontrado"}, status=404)


#Informa que a requisição vai ser do tipo DELETE e que ela recebe o book_id da URL.
@app.route("/books/<int:book_id>", methods=["DELETE"])
@admin_required # apenas usuario do tipo role admin pode executar 
def delete_book(book_id):
    global books #Informa ao python que vai alterar a variavel global books se não falar a variavel iria criar outra lista local
    #Armazena o tamanho original da lista antes de tentar remover o livro
    before_len = len(books)
    #criar uma nova lista chamada books que inclui todos os livros,
    # exceto aquele cujo id corresponde ao book_id que veio na URL.
    books = [b for b in books if b["id"] != book_id]
    #Verifica se a lista esta do mesmo tamanho e caso esteja significa que não apagou
    if len(books) == before_len:
        return json_response({"error": "Livro não encontrado"}, status=404)
    return json_response({"msg": f"Livro {book_id} removido"})#Retorna caso o livro tenha sido removido com sucesso. 

if __name__ == "__main__":
    #Inicia o servidor web flask
    app.run(debug=True, port=3344)#especifica a porta que vai rodar o servior