package br.com.fecaf.model;

public class Conta {

    private String numero,agencia;
    private double saldo;

    private Cliente cliente;

    public Conta (String numero,String agencia,Cliente cliente){
        this.numero = numero;
        this.agencia = agencia;
        this.saldo = 0;

        this.cliente = cliente;

    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    /*Depositar*/
    public void depositar (double valor) {
        System.out.println("Depositando");
        if (valor > 0) {
            saldo += valor;

        } else {
            System.out.println("Valor invalido");
        }
    }
        /*Saque*/
        public boolean sacar (double valor){
            if (valor >  0 && valor <= saldo){
                saldo -= valor;
                return true;
            }
            return false;
        }





}
