package br.com.fecaf.model;

public class Pokemon {
    private String nome,tipos,fraquezas,numRota;
    private int numPokemon;

    private String imagemUrl;

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public int getNumPokemon() {
        return numPokemon;
    }

    public void setNumPokemon(int numPokemon) {
        this.numPokemon = numPokemon;
    }

    public String getNumRota() {
        return numRota;
    }

    public void setNumRota(String numRota) {
        this.numRota = numRota;
    }

    public String getFraquezas() {
        return fraquezas;
    }

    public void setFraquezas(String fraquezas) {
        this.fraquezas = fraquezas;
    }

    public String getTipos() {
        return tipos;
    }

    public void setTipos(String tipos) {
        this.tipos = tipos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
