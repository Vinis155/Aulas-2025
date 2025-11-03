package br.com.TrinketStore.Model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("CLIENTE")
public class Cliente extends Usuario {

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pedido> pedidos = new ArrayList<>();

    // Construtor padrão exigido pelo JPA
    public Cliente() {
        super(); // Chama o construtor vazio da classe mãe (Usuario)
    }

    // Construtor personalizado
    public Cliente(String nome, String email, String senha) {
        super(nome, email, senha, "cliente");
    }

    // Getters e Setters
    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
        pedido.setCliente(this); // Mantém o vínculo bidirecional
    }

    public void removerPedido(Pedido pedido) {
        pedidos.remove(pedido);
        pedido.setCliente(null);
    }
}
