package telas;



import produtos.Produto;
import produtos.ProdutosService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ListaProdutosView extends JFrame {

    private JPanel painel;
    private JTable tabela;
    private ProdutosService produtosService;

    public ListaProdutosView(ProdutosService produtosService) {

        this.produtosService = produtosService;

        setTitle("Lista de Produtos");
        setLayout(new BorderLayout());

        // Painel principal
        painel = new JPanel(new BorderLayout());
        add(painel, BorderLayout.CENTER);

        // Criar tabela
        criarTabela();

        // Botão Voltar
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setPreferredSize(new Dimension(100, 40));
        btnVoltar.addActionListener(new BotaoVoltarHandler());

        JPanel painelBotao = new JPanel();
        painelBotao.add(btnVoltar);

        add(painelBotao, BorderLayout.SOUTH);

        setSize(700, 500);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void criarTabela() {

        String[] colunas = {"ID", "Nome", "Código de Barras", "Preço", "Estoque"};

        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);

        List<Produto> lista = produtosService.listarTodosProdutos();

        for (Produto p : lista) {
            Object[] linha = {
                    p.getId(),
                    p.getNome(),
                    p.getCodigoBarras(),
                    p.getPreco(),
                    p.getEstoque()
            };
            modelo.addRow(linha);
        }

        tabela = new JTable(modelo);
        tabela.setEnabled(false); // tabela só para visualização

        JScrollPane scroll = new JScrollPane(tabela);
        painel.add(scroll, BorderLayout.CENTER);
    }

    private class BotaoVoltarHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            dispose();
        }
    }
}
