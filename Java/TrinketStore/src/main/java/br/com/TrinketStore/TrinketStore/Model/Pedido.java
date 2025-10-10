package br.com.TrinketStore.TrinketStore.Model;
import java.util.List;

public class Pedido {

    private Long id;
    private Cliente cliente;
    private List <ItemPedido> itens;
    private String status; // pending, paid, picked_up

}
