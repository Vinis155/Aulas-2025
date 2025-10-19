package br.com.TrinketStore.Model;

public interface ItemPedido {
    Long getId();
    Produto getProduto();
    int getQuantidade();
    double getPrecoTotal();
}
