package br.com.TrinketStore.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private double preco;
    private String descricao;
    private long ativo;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Price> prices = new ArrayList<>();

    public List<Price> getPrices() {
        return prices;
    }

    public void addPrice(Price price) {
        prices.add(price);
        price.setProduto(this); // Garante que o vínculo bidirecional seja mantido
    }

    public void removePrice(Price price) {
        prices.remove(price);
        price.setProduto(null);
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public long isAtivo() {
        return ativo;
    }



}
