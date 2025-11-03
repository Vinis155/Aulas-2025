package br.com.TrinketStore.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario")
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    protected Long id;

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "nome_user", nullable = false)
    protected String nome;

    @NotBlank
    @Email
    @Column(name = "email_user", nullable = false)
    protected String email;

    @NotBlank
    @Size(min = 6)
    @Column(name = "senha_user", nullable = false)
    protected String senha;

    @NotBlank
    @Column(name = "ROLE", nullable = false)
    protected String role;

    @Column(name = "created_at")
    protected java.sql.Timestamp createdAt;

    public Usuario() {}

    public Usuario(String nome, String email, String senha, String role) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
