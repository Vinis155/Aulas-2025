package br.com.TrinketStore.TrinketStore.Model;

public class Admin extends User{
    private String codigoAcesso;

    public Admin(String nome, String email, String senha) {
        super(nome, email, senha, "admin");

    }

    public String getCodigoAcesso() {
        return codigoAcesso;
    }
}
