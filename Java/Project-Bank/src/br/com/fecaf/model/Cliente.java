package br.com.fecaf.model;

public class Cliente {
    //1° Pilar do POO {Encapsulamento}

    private String nome,cpf,rg,email;
    private int idade;

    //Getter and Setter

    //Get para buscar a variavel

    public String getNome (){
        return nome;
    }

    //Set para alternar a variavel

    public void setNome (String nome){
        // this serve para diferenciar o parametro e o atributo
        this.nome = nome;

    }

    public String getCpf (){
        return cpf;
    }

    public void setCpf (String cpf){
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }


    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
