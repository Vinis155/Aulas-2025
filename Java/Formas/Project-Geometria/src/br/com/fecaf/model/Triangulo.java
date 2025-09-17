package br.com.fecaf.model;

import java.util.Scanner;

public class Triangulo {
    double base, lado1, lado2;
    double area, perimetro,altura;
    String nome;

    Scanner scanner = new Scanner(System.in);

//Cadastrar o triangulo
    public void cadastrarTriangulo() {

        System.out.println("/*****************************************");
        System.out.println("/*           Cadastrar Triangulo         *");
        System.out.println("/*****************************************");


        while (true) {
            System.out.print("/Informe a base: ");

            // Chamei a biblioteca que faz o input do usuario

            //Variavel que são os lados
            base = scanner.nextDouble();


            System.out.print("Informe o lado2: ");
            lado1 = scanner.nextDouble();


            System.out.print("Informe o lado3: ");
            lado2 = scanner.nextDouble();

            System.out.println("Vericando se é um triangulo");

            try {
                Thread.sleep(2000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            //Verifica se é um triangulo


            if (base + lado1 > lado2 && base + lado2 > lado1 && lado1 + lado2 > base) {
                System.out.println("Triangulo cadastrado com Sucesso");
                scanner.nextLine();
                System.out.print("Informe o Nome: ");
                nome = scanner.nextLine();
                System.out.println("/*****************************************");
                break;
            } else {
                System.out.println("Isso não é um triangulo!");


                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void validarRetangulo() {
        // Descobre qual é a maior (hipotenusa) e compara Pitágoras direto
        if (base >= lado1 && base >= lado2) {
            if ((lado1 * lado1 + lado2 * lado2) == (base * base)) {
                System.out.println("Triângulo é retângulo!");
            } else {
                System.out.println("Não é retângulo!");
            }
        } else if (lado1 >= base && lado1 >= lado2) {
            if ((base * base + lado2 * lado2) == (lado1 * lado1)) {
                System.out.println("Triângulo é retângulo!");
            } else {
                System.out.println("Não é retângulo!");
            }
        } else { // lado2 é o maior
            if ((base * base + lado1 * lado1) == (lado2 * lado2)) {
                System.out.println("Triângulo é retângulo!");
            } else {
                System.out.println("Não é retângulo!");
            }
        }
    }





    //Calcular Area

        public void calcularArea () {
            System.out.println("/*****************************************");
            System.out.println("/*                Area                   *");
            System.out.println("/*****************************************");
            area = base * altura;
            System.out.println("/*****************************************");
        }


        public void calcularPerimetro () {
            System.out.println("/*****************************************");
            System.out.println("/*            Perimetro                  *");
            System.out.println("/*****************************************");
            perimetro = base + lado1 + lado2;
            System.out.println("/*****************************************");

        }

        public void verificarTriangulo () {
            System.out.println("/*****************************************");
            System.out.println("/*            Verificar Tipo             *");
            System.out.println("/*****************************************");


            if (base == lado1 && base == lado2) {
                System.out.println("Esse é um Triangulo equilatero");
            } else if (base != lado1 && base != lado2 && lado1 != lado2) {
                System.out.println("Esse é um Triangulo Escaleno");

            } else {
                System.out.println("Isosceles");
            }



    }
        public void exibirInformacoes() {
            System.out.println("/*****************************************");
            System.out.println("/*       Informações do Triangulo        *");
            System.out.println("/*****************************************");
            System.out.println("Nome: " + nome);
            System.out.println("Base: " + base);
            System.out.println("Lado 1:" + lado1);
            System.out.println("Lado 2:" + lado2);
            System.out.println("Area: " + area);
            System.out.println("Perimetro: " + perimetro);
            System.out.println("Altura" + altura);
            System.out.println("/*****************************************");
            System.out.println("*    Indo para o menu    *");
        }


    }


