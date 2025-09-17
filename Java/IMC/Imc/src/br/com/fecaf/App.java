package br.com.fecaf;

import br.com.fecaf.model.Imc;

public class App {
    public static void main(String[] args) {
        System.out.println("****************************");
        System.out.println("*      Projeto Imc         *");
        System.out.println("****************************");


        Pessoa pessoa = new Pessoa();
        pessoa.nome = "Vinicius";
        pessoa.idade = 23;
        pessoa.altura = 1.69;
        pessoa.peso = 74;
        pessoa.cpf = 1223;



        Imc imc = new Imc();


        double imcPessoa = imc.calcularImc(pessoa.peso, pessoa.altura);
        String statusImcPessoa = imc.ClassificarImc();

        pessoa.exibirInformacoes(imcPessoa, statusImcPessoa);

    }
}