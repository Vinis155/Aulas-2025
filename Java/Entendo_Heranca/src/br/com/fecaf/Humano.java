package br.com.fecaf;

public class Humano extends serVivo{
    protected String cpf;

    public Humano(int idade, String nome, String cpf){
        super(idade,nome);
        this.cpf = cpf;


    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
