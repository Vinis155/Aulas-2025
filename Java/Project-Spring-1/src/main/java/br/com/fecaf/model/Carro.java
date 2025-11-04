package br.com.fecaf.model;

public class Carro {
    private int id;
    private String modelo,marca,motor,combustivel,cor,transmissao,status;
    private double preco;
    private int ano;

    private Consumo consumo_km_l;

    public static class Consumo {
        private double cidade;
        private double estrada;

        public double getCidade() { return cidade; }
        public void setCidade(double cidade) { this.cidade = cidade; }

        public double getEstrada() { return estrada; }
        public void setEstrada(double estrada) { this.estrada = estrada; }
    }

    // Getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getMotor() { return motor; }
    public void setMotor(String motor) { this.motor = motor; }

    public String getCombustivel() { return combustivel; }
    public void setCombustivel(String combustivel) { this.combustivel = combustivel; }

    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }

    public String getTransmissao() { return transmissao; }
    public void setTransmissao(String transmissao) { this.transmissao = transmissao; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }

    public Consumo getConsumo_km_l() { return consumo_km_l; }
    public void setConsumo_km_l(Consumo consumo_km_l) { this.consumo_km_l = consumo_km_l; }
}
