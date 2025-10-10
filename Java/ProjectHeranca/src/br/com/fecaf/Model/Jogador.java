package br.com.fecaf.Model;

public class Jogador extends Pessoa{
    /*Atributo especifico*/
    private  int numeroCamisa;
    private String posicao;


    public Jogador (String CPF, String posicao){
        super(CPF);
        this.posicao = posicao;

    }

    /*Ação especifica*/
    public void driblar(){
        System.out.println("O jogador deu uma caneta");
    }


    public int getNumeroCamisa() {
        return numeroCamisa;
    }

    public void setNumeroCamisa(int numeroCamisa) {
        this.numeroCamisa = numeroCamisa;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }
}
