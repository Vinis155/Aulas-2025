package br.com.fecaf.model;

import java.util.Scanner;

public class Circulo {
    public double raio, diametro, area, perimetro;

    Scanner scanner = new Scanner(System.in);

    public void cadastrarCirculo(){
        System.out.println("**************************");
        System.out.println("*     Calcular raio      *");
        System.out.println("**************************");
        System.out.println("*Informe o raio: ");

        raio = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("**************************");

    }


    public void calcularArea(){
        System.out.println("**************************");
        System.out.println("*     Calcular Area      *");
        System.out.println("**************************");

        //Math.Pi é uma biblioteca
        area = Math.PI * Math.pow(raio,2);
    }


    public void calcularPerimetroC(){
        System.out.println("**************************");
        System.out.println("*  Calculando Perimetro *");
        System.out.println("**************************");
        perimetro = 2 * Math.PI * raio;
        System.out.println("**************************");

    }
    public void exibirInformacoes(){
        System.out.println("**************************");
        System.out.println("*  Informações do Circulo *");
        System.out.println("**************************");
        System.out.println("Raio: "+ raio);
        System.out.println("Area: "+ area);
        System.out.println("Perimetro" + perimetro);
        System.out.println("**************************");
        System.out.println("*    Indo para o menu    *");

        try{Thread.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

