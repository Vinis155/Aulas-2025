use std::collections::VecDeque;


//Inserir 10 e 20 no ultimo e o 5 na frente 
fn main() {
    let mut deque: VecDeque<i32> = VecDeque::new();


    // Inserindo no final
    deque.push_back(10);
    deque.push_back(20);


    // Inserindo no início
    deque.push_front(5);


    println!("{:?}", deque); // Saída: [5, 10, 20]
}



// para remover a primeira e ultima da pilha

fn main1() {
    let mut deque = VecDeque::from([1, 2, 3, 4, 5]);

    println!("{:?}", deque.pop_front()); // Saída: Some(1)
    println!("{:?}", deque.pop_back());  // Saída: Some(5)

    println!("{:?}", deque); // Saída: [2, 3, 4]
}

fn main2(){
    let mut x = 10;
    let y = &mut x;
    *y + 5;
    println! ("x agora é {}", x); //x agora é 15
}


fn main3() {
    let x = 42;
    let r = &x;

    println!("Valor: {}", *r);
    println!("Endereço: {:p}", r); // Imprime o endereço da referência
}


// Não se comunica
fn dobrar(mut valor: i32) {
    valor = valor * 2;
    println!("Dentro da função: {}", valor);
}

fn main() {
    let x = 10;
    dobrar(x);
    println!("Fora da função: {}, ainda é 10, não mudou", x);
}


//Agora se comunica pois tem o ponteiro &mut
fn dobrar(valor: &mut i32) {
    *valor = *valor * 2; // desreferencia para acessar o dado
    println!("Dentro da função: {}", valor);
}

fn main() {
    let mut x = 10;
    dobrar(&mut x);
    println!("Fora da função: {}, agora mudou para 20", x);
}


//Criando hashmap em rust

use std::collections::HashMap;

fn main() {
    // Criando um HashMap vazio
    let mut capitais = HashMap::new();

    // Inserindo elementos
    capitais.insert("Brasil", "Brasília");
    capitais.insert("França", "Paris");
    capitais.insert("Japão", "Tóquio");

    println!("{:?}", capitais);
}

if let some (capital) = capitais.get("Brasil") {
    println! ("A capital do Brasil é {}", capital)
}


//Atualizar valor

capitais.insert("Brasil","Rio de Janeiro");

//Remover

capitais.remove("França");

//Iterar sobre todos os pares

for (pais,capital) in &capitais {
    println! ("{} -> {}",pais,capital);
}


//Hashmap + tupla

use std::collections::HashMap;

fn main() {
    let mut usuarios: HashMap<u32, (String, i32, f64, bool, char)> = HashMap::new();

    usuarios.insert(1, ("Ana".to_string(), 25, 3500.0, true, 'A'));
    usuarios.insert(2, ("Bruno".to_string(), 30, 4200.5, false, 'B'));
}

// Buscar "linha" pelo ID
if let Some(usuario) = usuarios.get(&1) {
    println!("Registro 1 => {:?}", usuario);
    println!("Nome: {}, Salário: {}", usuario.0, usuario.2);
}