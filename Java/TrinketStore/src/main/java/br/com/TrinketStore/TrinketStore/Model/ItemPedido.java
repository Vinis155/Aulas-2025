package br.com.TrinketStore.TrinketStore.Model;

public interface ItemPedido {
    Long getId();
    produto getProduto();
    int getQuantidade();
    double getPrecoTotal();
}
