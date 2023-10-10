package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInicial {
    private JFrame frame;
    private JTabbedPane tabbedPane;

    public TelaInicial() {
        frame = new JFrame("OC - System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);

        tabbedPane = new JTabbedPane();
        frame.add(tabbedPane);

        // Aba de Cadastro de Clientes
        JPanel clientesPanel = new JPanel();
        clientesPanel.add(new JLabel("Aba de Cadastro de Clientes"));
        tabbedPane.addTab("Clientes", clientesPanel);

        // Aba de Cadastro de Produtos
        JPanel produtosPanel = new JPanel();
        produtosPanel.add(new JLabel("Aba de Cadastro de Produtos"));
        tabbedPane.addTab("Produtos", produtosPanel);

        // Aba de Orçamentos
        JPanel orcamentosPanel = new JPanel();
        orcamentosPanel.add(new JLabel("Aba de Orçamentos"));
        tabbedPane.addTab("Orçamentos", orcamentosPanel);

        // Menu
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu menuCadastro = new JMenu("Cadastro");
        menuBar.add(menuCadastro);

        JMenuItem menuItemClientes = new JMenuItem("Clientes");
        menuItemClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedComponent(clientesPanel);
            }
        });
        menuCadastro.add(menuItemClientes);

        JMenuItem menuItemProdutos = new JMenuItem("Produtos");
        menuItemProdutos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane.setSelectedComponent(produtosPanel);
            }
        });
        menuCadastro.add(menuItemProdutos);

        JMenu menuOrcamentos = new JMenu("Orçamentos");
        menuBar.add(menuOrcamentos);

        JMenuItem menuItemNovoOrcamento = new JMenuItem("Novo Orçamento");
        menuItemNovoOrcamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FormularioOrcamento formulario = new FormularioOrcamento();
                formulario.setVisible(true);
            }
        });
        menuOrcamentos.add(menuItemNovoOrcamento);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TelaInicial();
            }
        });
    }
}
