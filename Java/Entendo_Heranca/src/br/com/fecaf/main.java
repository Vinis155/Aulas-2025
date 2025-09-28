package br.com.fecaf;

import java.util.Scanner;

public class main {



    public static void main(String[] args) {


//      Estou instanciando como Humano pois todo humano possui cpf mas nem todo ser vivo possui
      Humano humano = new Humano(23, "Vinicius","124432434732");

      /* Criei uma class cachorro para mostrar a diferença pois como cachorro não possui CPF
      * ou outro atributo especifico, não é necessario chamar outra class além do SerVivo*/
      serVivo cachorro = new Cachorro (4, "White");


      System.out.println("O nome do Humano é " + humano.nome + " e a idade dele(a) é " + humano.idade + "E o CPF dele(a) é " + humano.getCpf());

      System.out.println("O nome do Cachrro é " + cachorro.nome + " e a idade dele é " + cachorro.idade + "anos");




    }
}
