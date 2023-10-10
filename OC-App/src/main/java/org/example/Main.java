package org.example;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

class Marmore {
    private String nome;
    private double precoMetroQuadrado;

    public Marmore(String nome, double precoMetroQuadrado) {
        this.nome = nome;
        this.precoMetroQuadrado = precoMetroQuadrado;
    }

    public String getNome() {
        return nome;
    }

    public double getPrecoMetroQuadrado() {
        return precoMetroQuadrado;
    }
}

class Rodape {
    private double precoMetroQuadrado;
    private double comprimento;
    private double largura;
    private double espessura;

    public Rodape(double precoMetroQuadrado, double comprimento, double largura, double espessura) {
        this.precoMetroQuadrado = precoMetroQuadrado;
        this.comprimento = comprimento;
        this.largura = largura;
        this.espessura = espessura;
    }

    public double getPrecoMetroQuadrado() {
        return precoMetroQuadrado;
    }

    public double calcularArea() {
        return comprimento * largura;
    }
}

class OrcamentoItem {
    private Marmore marmore;
    private List<Rodape> rodapes = new ArrayList<>();
    private double comprimento;
    private double largura;
    private double espessura;

    public OrcamentoItem(Marmore marmore, double comprimento, double largura, double espessura) {
        this.marmore = marmore;
        this.comprimento = comprimento;
        this.largura = largura;
        this.espessura = espessura;
    }

    public void adicionarRodape(Rodape rodape) {
        rodapes.add(rodape);
    }

    public double calcularAreaMarmore() {
        return comprimento * largura * espessura;
    }

    public double calcularSubtotalMarmore() {
        return marmore.getPrecoMetroQuadrado() * calcularAreaMarmore();
    }

    public double calcularSubtotalRodapes() {
        double subtotalRodapes = 0;
        for (Rodape rodape : rodapes) {
            subtotalRodapes += rodape.getPrecoMetroQuadrado() * rodape.calcularArea();
        }
        return subtotalRodapes;
    }

    public double calcularSubtotalTotal() {
        return calcularSubtotalMarmore() + calcularSubtotalRodapes();
    }
}

class Orcamento {
    private List<OrcamentoItem> itens = new ArrayList<>();

    public void adicionarItem(OrcamentoItem item) {
        itens.add(item);
    }

    public double calcularTotal() {
        double total = 0;
        for (OrcamentoItem item : itens) {
            total += item.calcularSubtotalTotal();
        }
        return total;
    }
}

public class Main {
    public static void main(String[] args) {
        Marmore marmore1 = new Marmore("Carrara", 50.0);
        Marmore marmore2 = new Marmore("Granito", 30.0);

        TelaOrcamento orcamento = new TelaOrcamento();

        while (true) {
            String nomeMarmore = JOptionPane.showInputDialog("Digite o nome do mármore (Carrara ou Granito) ou 'finalizar' para encerrar:");

            if (nomeMarmore == null || nomeMarmore.equalsIgnoreCase("finalizar")) {
                break;
            }

            Marmore marmoreSelecionado = nomeMarmore.equalsIgnoreCase("Carrara") ? marmore1 : marmore2;

            double comprimento = Double.parseDouble(JOptionPane.showInputDialog("Digite o comprimento da pedra (metros):"));
            double largura = Double.parseDouble(JOptionPane.showInputDialog("Digite a largura da pedra (metros):"));
            double espessura = Double.parseDouble(JOptionPane.showInputDialog("Digite a espessura da pedra (metros):"));

            OrcamentoItem item = new OrcamentoItem(marmoreSelecionado, comprimento, largura, espessura);

            while (true) {
                double precoRodape = Double.parseDouble(JOptionPane.showInputDialog("Digite o preço do rodapé por metro quadrado:"));
                double comprimentoRodape = Double.parseDouble(JOptionPane.showInputDialog("Digite o comprimento do rodapé (metros):"));
                double larguraRodape = Double.parseDouble(JOptionPane.showInputDialog("Digite a largura do rodapé (metros):"));
                double espessuraRodape = Double.parseDouble(JOptionPane.showInputDialog("Digite a espessura do rodapé (metros):"));

                Rodape rodape = new Rodape(precoRodape, comprimentoRodape, larguraRodape, espessuraRodape);
                item.adicionarRodape(rodape);

                int escolha = JOptionPane.showConfirmDialog(null, "Deseja adicionar outro rodapé?", "Adicionar Rodapé", JOptionPane.YES_NO_OPTION);
                if (escolha != JOptionPane.YES_OPTION) {
                    break;
                }
            }

            orcamento.adicionarItem(item);
        }

    }
}
