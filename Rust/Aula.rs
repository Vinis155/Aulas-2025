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