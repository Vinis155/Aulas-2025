package br.com.TrinketStore.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedidoEntity> itens;

    private String status; // pending, paid, picked_up

    // Construtores
    public Pedido() {}

    public Pedido(Cliente cliente, List<ItemPedidoEntity> itens, String status) {
        this.cliente = cliente;
        this.itens = itens;
        this.status = status;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemPedidoEntity> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoEntity> itens) {
        this.itens = itens;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
