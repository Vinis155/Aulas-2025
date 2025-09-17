package br.com.fecaf.model;

import java.util.Scanner;

public class Retangulo {

    //Atributos

    double lado1,lado2,area,perimetro;
    String nome;

    public void cadastrarRetangulo(){

        System.out.println("/*****************************************");
        System.out.println("/*            Cadastrar Retangulo        *");
        System.out.println("/*****************************************");
        System.out.print("/Informe o lado1: ");

        // Chamei a biblioteca que faz o input do usuario
        Scanner scanner = new Scanner (System.in);
        //Variavel que são os lados
        lado1 = scanner.nextDouble();


        System.out.print("Informe o lado2: ");
        lado2 = scanner.nextDouble();

        scanner.nextLine();
        System.out.print("Informe o Nome: ");
        nome = scanner.nextLine();
        System.out.println("/*****************************************");
    }

    public void calcularArea() {
        System.out.println("/*****************************************");
        System.out.println("/*                Area                   *");
        System.out.println("/*****************************************");
        area = lado1 * lado2;

    }

public  void calcularPerimetro(){
    System.out.println("/*****************************************");
    System.out.println("/*            Perimetro                  *");
    System.out.println("/*****************************************");
    perimetro = (lado1 * 2) + (lado2 * 2);
    System.out.println("/*****************************************");

}

public void exibirInformacoes(){
    System.out.println("/*****************************************");
    System.out.println("/*            Informações               *");
    System.out.println("/*****************************************");
    System.out.println("Nome: " + nome);
    System.out.println("Lado 1: " + lado1);
    System.out.println("Lado 2:" + lado2);
    System.out.println("Area: " + area);
    System.out.println("Perimetro: " + perimetro);
    System.out.println("/*****************************************");
    System.out.println("*    Indo para o menu    *");

    try{Thread.sleep(2000);

    } catch (InterruptedException e) {
        e.printStackTrace();
    }
}

public void validarQuadrado(){
        if (lado1 == lado2) {
            System.out.println("É um quadrado!");
        }
            else {
                System.out.println("Não é um quadrado!");
            }

        }

}


