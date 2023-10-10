package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FormularioOrcamento extends JFrame {
    private JTextField campoNomeCliente;
    private JTextField campoNomeitem;
    private JTextField campoQuantidade;
    private JTextField campoValorUnitario;
    private JTextField campoComprimento;
    private JTextField campoLargura;
    private JTextField campoEspessura;
    private JButton botaoAdicionarItem;
    private JButton botaoFinalizar;
    private JTable tabelaItens;
    private DefaultTableModel modeloTabela;
    private List<ItemOrcamento> listaItens;

    public FormularioOrcamento() {
        setTitle("Novo Orçamento");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        listaItens = new ArrayList<>();

        JPanel painel = new JPanel(new BorderLayout());

        JPanel painelCliente = new JPanel(new GridLayout(2, 2));
        JLabel labelNomeCliente = new JLabel("Nome do Cliente:");
        campoNomeCliente = new JTextField();
        painelCliente.add(labelNomeCliente);
        painelCliente.add(campoNomeCliente);

        JPanel painelItens = new JPanel(new GridLayout(8, 2));
        JLabel labelNomeItem = new JLabel("Item");
        campoNomeitem = new JTextField();
        JLabel labelQuantidade = new JLabel("Quantidade:");
        campoQuantidade = new JTextField();
        JLabel labelValorUnitario = new JLabel("Valor Unitário:");
        campoValorUnitario = new JTextField();
        JLabel labelComprimento = new JLabel("Comprimento (metros):");
        campoComprimento = new JTextField();
        JLabel labelLargura = new JLabel("Largura (metros):");
        campoLargura = new JTextField();
        JLabel labelEspessura = new JLabel("Espessura (metros):");
        campoEspessura = new JTextField();
        botaoAdicionarItem = new JButton("Adicionar Item");
        botaoFinalizar = new JButton("Finalizar");

        botaoAdicionarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarItem();
            }
        });

        botaoFinalizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizarOrcamento();
            }
        });

        painelItens.add(labelNomeItem);
        painelItens.add(campoNomeitem);
        painelItens.add(labelQuantidade);
        painelItens.add(campoQuantidade);
        painelItens.add(labelValorUnitario);
        painelItens.add(campoValorUnitario);
        painelItens.add(labelComprimento);
        painelItens.add(campoComprimento);
        painelItens.add(labelLargura);
        painelItens.add(campoLargura);
        painelItens.add(labelEspessura);
        painelItens.add(campoEspessura);
        painelItens.add(new JLabel()); // Espaço em branco
        painelItens.add(botaoAdicionarItem);
        painelItens.add(new JLabel()); // Espaço em branco
        painelItens.add(botaoFinalizar);

        modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("Item");
        modeloTabela.addColumn("Quantidade");
        modeloTabela.addColumn("Valor Unitário");
        modeloTabela.addColumn("Comprimento (m)");
        modeloTabela.addColumn("Largura (m)");
        modeloTabela.addColumn("Espessura (m)");

        tabelaItens = new JTable(modeloTabela);

        JScrollPane scrollPane = new JScrollPane(tabelaItens);

        painel.add(painelCliente, BorderLayout.NORTH);
        painel.add(scrollPane, BorderLayout.CENTER);
        painel.add(painelItens, BorderLayout.SOUTH);

        add(painel);
        setLocationRelativeTo(null);
    }

    private void adicionarItem() {
        try {
            String nome = String.valueOf(campoNomeitem.getText());
            int quantidade = Integer.parseInt(campoQuantidade.getText());
            double valorUnitario = Double.parseDouble(campoValorUnitario.getText());
            double comprimento = Double.parseDouble(campoComprimento.getText());
            double largura = Double.parseDouble(campoLargura.getText());
            double espessura = Double.parseDouble(campoEspessura.getText());

            ItemOrcamento item = new ItemOrcamento(nome, quantidade, valorUnitario, comprimento, largura, espessura);
            listaItens.add(item);

            // Adicionar o item à tabela
            modeloTabela.addRow(new Object[]{nome, quantidade, valorUnitario, comprimento, largura, espessura});

            // Limpar os campos após adicionar o item
            campoNomeitem.setText("");
            campoQuantidade.setText("");
            campoValorUnitario.setText("");
            campoComprimento.setText("");
            campoLargura.setText("");
            campoEspessura.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira números válidos nos campos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void finalizarOrcamento() {
        // Implemente a lógica para finalizar o orçamento e mostrar os detalhes

        // Por exemplo, você pode calcular o total com base nos itens na listaItens
        double total = calcularTotal();

        // Exibir os detalhes do orçamento
        StringBuilder detalhes = new StringBuilder();
        detalhes.append("Cliente: ").append(campoNomeCliente.getText()).append("\n");
        detalhes.append("Itens do Orçamento:\n");

        for (ItemOrcamento item : listaItens) {
            detalhes.append("Item: ").append(item.getNome()).append("\n");
            detalhes.append("Quantidade: ").append(item.getQuantidade()).append("\n");
            detalhes.append("Valor Unitário: ").append(item.getValorUnitario()).append("\n");
            detalhes.append("Comprimento: ").append(item.getComprimento()).append(" metros\n");
            detalhes.append("Largura: ").append(item.getLargura()).append(" metros\n");
            detalhes.append("Espessura: ").append(item.getEspessura()).append(" metros\n");
            detalhes.append("Subtotal: ").append(item.calcularSubtotal()).append("\n");
            detalhes.append("\n");
        }

        detalhes.append("Total: ").append(total);

        JOptionPane.showMessageDialog(this, detalhes.toString(), "Detalhes do Orçamento", JOptionPane.INFORMATION_MESSAGE);
    }

    private double calcularTotal() {
        double total = 0;
        for (ItemOrcamento item : listaItens) {
            total += item.calcularSubtotal();
        }
        return total;
    }
}
