package br.com.fecaf.Model;

public class Juiz extends Pessoa{

    public Juiz(String CPF) {
        super(CPF);
    }

    public void aplicarCartao(){
        System.out.println("O juiz aplicou o cartão para o jogador");
    }

}
