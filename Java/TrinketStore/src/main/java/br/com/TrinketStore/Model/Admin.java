package br.com.TrinketStore.Model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends Usuario {
    private String codigoAcesso;




    public Admin(String nome, String email, String senha) {
        super(nome, email, senha, "admin");

    }

    public String getCodigoAcesso() {
        return codigoAcesso;
    }

    public Admin() {
        super(); // chama o construtor padrão da classe Usuario
    }
}
