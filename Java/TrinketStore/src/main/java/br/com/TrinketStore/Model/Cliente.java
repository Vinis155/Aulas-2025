package br.com.TrinketStore.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("CLIENTE")
public class Cliente extends Usuario {

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos = new ArrayList<>();

    // Construtor padrão necessário para JPA
    public Cliente() {
        super(String .class.getName(), String.class.getName(), String.class.getName(), "cliente");
    }

    public Cliente(String nome, String email, String senha) {
        super(nome, email, senha, "cliente");
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
        pedido.setCliente(this); // garante o relacionamento bidirecional
    }

    public void removerPedido(Pedido pedido) {
        pedidos.remove(pedido);
        pedido.setCliente(null);
    }
}
