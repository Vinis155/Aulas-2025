package br.com.TrinketStore.Model;

public interface ItemPedido {
    Long getId();
    produto getProduto();
    int getQuantidade();
    double getPrecoTotal();
}
