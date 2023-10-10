package org.example;

public class ItemOrcamento {

    private String nome;
    private int quantidade;
    private double valorUnitario;
    private double comprimento;
    private double largura;
    private double espessura;

    public ItemOrcamento(String nome, int quantidade, double valorUnitario, double comprimento, double largura, double espessura) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.comprimento = comprimento;
        this.largura = largura;
        this.espessura = espessura;
    }

    public String getNome() {
        return nome;
    }
    public int getQuantidade() {
        return quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public double getComprimento() {
        return comprimento;
    }

    public double getLargura() {
        return largura;
    }

    public double getEspessura() {
        return espessura;
    }

    public double calcularSubtotal() {
        return quantidade * valorUnitario * comprimento * largura * espessura;
    }
}
