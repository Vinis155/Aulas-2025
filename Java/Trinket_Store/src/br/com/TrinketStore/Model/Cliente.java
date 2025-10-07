package br.com.TrinketStore.Model;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends User{

    private List <Pedido> pedidos;

    public Cliente (String nome, String email, String senha) {
        super(nome, email, senha, "cliente");
        this.pedidos = new ArrayList<>();
    }


    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }


}
