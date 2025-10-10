package br.com.fecaf.Model;

public class Torcedor extends Pessoa{

    public Torcedor(String CPF) {
        super(CPF);
    }

    public void comprarIngresso(){
        System.out.println("O cara pagou caro e o time perdeu");
    }

}
