package br.com.TrinketStore.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class ItemPedidoEntity implements ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_items")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Produto produto;

    @Column(name = "qty_items", nullable = false)
    private int quantidade;

    @Column(name = "unit_amount", nullable = false)
    private int valorUnitario;

    @Column(name = "subtotal_amount", nullable = false)
    private int subtotal;

    // 🔹 Construtores
    public ItemPedidoEntity() {}

    public ItemPedidoEntity(Pedido pedido, Produto produto, int quantidade, int valorUnitario) {
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.subtotal = quantidade * valorUnitario;
    }

    // --- Métodos da interface ItemPedido ---
    @Override
    public Long getId() { return id; }

    @Override
    public Produto getProduto() { return produto; }

    @Override
    public int getQuantidade() { return quantidade; }

    @Override
    public double getPrecoTotal() { return subtotal; }


    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }

    public void setProduto(Produto produto) { this.produto = produto; }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        this.subtotal = this.valorUnitario * quantidade;
    }

    public int getValorUnitario() { return valorUnitario; }
    public void setValorUnitario(int valorUnitario) {
        this.valorUnitario = valorUnitario;
        this.subtotal = this.valorUnitario * this.quantidade;
    }

    public int getSubtotal() { return subtotal; }
}
