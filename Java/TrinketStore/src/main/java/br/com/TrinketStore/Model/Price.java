package br.com.TrinketStore.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "prices")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_price")
    private Long id;

    @Column(name = "stripe_price_id", unique = true, nullable = false, length = 50)
    private String stripePriceId;

    @Column(name = "amount_price", nullable = false)
    private Integer amount;

    @Column(name = "currency_price", length = 10, nullable = false)
    private String currency = "BRL";

    @Column(name = "vigente_price", nullable = false)
    private boolean vigente = true;

    // 🔗 Relação com Produto
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Produto produto;

    // --- Getters e Setters ---
    public Long getId() { return id; }

    public String getStripePriceId() { return stripePriceId; }
    public void setStripePriceId(String stripePriceId) { this.stripePriceId = stripePriceId; }

    public Integer getAmount() { return amount; }
    public void setAmount(Integer amount) { this.amount = amount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public boolean isVigente() { return vigente; }
    public void setVigente(boolean vigente) { this.vigente = vigente; }

    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }
}
