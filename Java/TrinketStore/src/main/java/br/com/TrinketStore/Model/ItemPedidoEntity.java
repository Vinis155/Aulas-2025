package br.com.TrinketStore.Model;

import jakarta.persistence.*;

@Entity
public class ItemPedidoEntity implements ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private int quantidade;

    public ItemPedidoEntity() {}

    public ItemPedidoEntity(Pedido pedido, Produto produto, int quantidade) {
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    @Override
    public Long getId() { return id; }

    @Override
    public Produto getProduto() { return produto; }

    @Override
    public int getQuantidade() { return quantidade; }

    @Override
    public double getPrecoTotal() { return produto.getPreco() * quantidade; }

    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }

    public void setProduto(Produto produto) { this.produto = produto; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
}
