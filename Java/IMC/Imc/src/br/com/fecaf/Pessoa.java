package br.com.fecaf;



public class Pessoa {
     double altura;
     int idade, cpf, peso;
     String nome;


    public void exibirInformacoes(double imc,String statusImcPessoa) {
        System.out.println("****************************");
        System.out.println("*    Informações da Pessoa *");
        System.out.println("****************************");
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Peso: " + peso);
        System.out.println("Altura: " + altura);
        System.out.println("CPF: " + cpf);
        System.out.println("IMC: "+ imc);
        System.out.println("Status Imc: " + statusImcPessoa);
    }
}