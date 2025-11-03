package br.com.TrinketStore.Model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends Usuario {

    @Column(name = "codigo_acesso", length = 50)
    private String codigoAcesso;

    // 🔹 Construtor padrão exigido pelo JPA
    public Admin() {
        super(); // chama o construtor padrão da classe Usuario
    }

    // 🔹 Construtor personalizado para criar Admin manualmente
    public Admin(String nome, String email, String senha, String codigoAcesso) {
        super(nome, email, senha, "admin");
        this.codigoAcesso = codigoAcesso;
    }

    // 🔹 Getter e Setter
    public String getCodigoAcesso() {
        return codigoAcesso;
    }

    public void setCodigoAcesso(String codigoAcesso) {
        this.codigoAcesso = codigoAcesso;
    }
}
