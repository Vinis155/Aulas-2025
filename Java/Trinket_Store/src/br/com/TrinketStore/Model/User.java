package br.com.TrinketStore.Model;


public abstract class User {
    protected Long id;
    protected String nome;
    protected String email;
    protected String senha;
    protected String role; // "ADMIN" ou "CLIENTE"

    public User(String nome, String email, String senha, String role) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = role;
    }

    // Getters e Setters
}
