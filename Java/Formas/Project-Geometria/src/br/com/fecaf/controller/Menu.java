package br.com.fecaf.controller;

import br.com.fecaf.model.Circulo;
import br.com.fecaf.model.Retangulo;
import br.com.fecaf.model.Triangulo;

import java.util.Scanner;

public class Menu {

    Scanner scanner = new Scanner(System.in);

    Retangulo retangulo = new Retangulo();
    Circulo circulo = new Circulo();
    Triangulo triangulo = new Triangulo();


    public void executarMenu() {

        boolean exit = false;
        // ! é pra mostrar que é diferente

        while (!exit) {
            System.out.println("/*****************************************/");
            System.out.println("/*               Menu                    */");
            System.out.println("/*****************************************/");
            System.out.println("/* 1 - Retangulo                         */");
            System.out.println("/* 2 - Circulo                           */");
            System.out.println("/* 3 - Triangulo                         */");
            System.out.println("/* 4 - Sair                              */");
            System.out.println("/******************************************");

            System.out.println("/Informe uma opção");
            int userOption = scanner.nextInt();


            switch (userOption) {
                case 1:

                    boolean exitRetangulo = false;
                    while (!exitRetangulo) {
                        System.out.println("/*****************************************");
                        System.out.println("/*               Menu                    *");
                        System.out.println("/*****************************************");
                        System.out.println("1- Cadastrar Retângulo");
                        System.out.println("2- Calcular Area");
                        System.out.println("3- Calcular Perimetro");
                        System.out.println("4- Exibir Informações");
                        System.out.println("5- Sair");
                        System.out.println("/*****************************************");


                        int userOptionRetangulo = scanner.nextInt();
                        scanner.nextLine();

                        switch (userOptionRetangulo) {

                            case 1:
                                retangulo.cadastrarRetangulo();
                                break;

                            case 2:
                                retangulo.calcularArea();
                                break;

                            case 3:
                                retangulo.calcularPerimetro();
                                break;

                            case 4:
                                System.out.println("Exibindo informações do Retangulo");
                                retangulo.exibirInformacoes();
                                break;
                            case 5:
                                System.out.println("Saindo...");
                                exitRetangulo = true;
                                break;
                        }
                    }
break;
                case 2:

                    boolean exitCirculo = false;
                    while (!exitCirculo) {
                        System.out.println("/*****************************************");
                        System.out.println("/*               Menu                    *");
                        System.out.println("/*****************************************");
                        System.out.println("1- Cadastrar Circulo");
                        System.out.println("2- Calcular Area");
                        System.out.println("3- Calcular Perimetro");
                        System.out.println("4- Exibir Informações");
                        System.out.println("5- Sair");
                        System.out.println("/*****************************************");


                        int userOptionCirculo = scanner.nextInt();
                        scanner.nextLine();

                        switch (userOptionCirculo) {

                            case 1:
                                circulo.cadastrarCirculo();
                                break;

                            case 2:
                                circulo.calcularArea();
                                break;


                            case 3:
                                circulo.calcularPerimetroC();
                                break;

                            case 4:
                                System.out.println("Exibindo informações do Circulo");
                                circulo.exibirInformacoes();
                                break;
                            case 5:
                                System.out.println("Saindo...");
                                exitCirculo = false;
                                break;
                        }
                    }
                    break;



                case 3:
                    boolean exitTriangulo = false;
                    while (!exitTriangulo) {
                        System.out.println("/*****************************************");
                        System.out.println("/*               Menu                    *");
                        System.out.println("/*****************************************");
                        System.out.println("*1- Cadastrar Triangulo                   *");
                        System.out.println("*2- Validar se é um triangulo retangulo   *");


                        int userOptionTriangulo = scanner.nextInt();
                        scanner.nextLine();

                        switch (userOptionTriangulo) {

                            case 1:
                                triangulo.cadastrarTriangulo();
                                break;

                            case 2:
                                triangulo.validar_Possivel_Retangulo();
                                break;

                        }
                    }
            }
        }
    }
}