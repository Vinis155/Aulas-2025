package br.com.fecaf.model;


public class Imc {
    public double imc;
    public String ClassificarImc;


    public double calcularImc(int peso, double altura) {
        imc = peso / (altura * altura);
        System.out.println("O seu Imc é " + imc);
        return imc;
    }

    public String ClassificarImc() {
        if (imc < 18.5) {
            ClassificarImc = ("Abaixo do peso");
        } else if (imc < 29.99) {
            ClassificarImc = ("Normal");
        } else if (imc < 29.99) {
            ClassificarImc = ("Sobrepeso");
        } else {
            ClassificarImc = ("Obeso");

        }

        return ClassificarImc;}
}