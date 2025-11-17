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

        painel = new JPanel(new BorderLayout());
        add(painel, BorderLayout.CENTER);

        criarTabela();
        criarBotoes();

        setSize(700, 500);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void criarTabela() {

        String[] colunas = {"ID", "Nome", "Código de Barras", "Preço", "Estoque"};
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0){

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;  // Nenhuma célula é editável
        }};

        List<Produto> lista = produtosService.listarTodosProdutos();

        for (Produto p : lista) {
            modelo.addRow(new Object[]{
                    p.getId(),
                    p.getNome(),
                    p.getCodigoBarras(),
                    p.getPreco(),
                    p.getEstoque()
            });
        }

        tabela = new JTable(modelo);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scroll = new JScrollPane(tabela);
        painel.add(scroll, BorderLayout.CENTER);
    }

    private void criarBotoes() {
        JPanel painelBotoes = new JPanel();

        JButton btnEditar = new JButton("Visualizar / Alterar Preço");
        JButton btnVoltar = new JButton("Voltar");

        btnEditar.addActionListener(new BotaoEditarHandler());
        btnVoltar.addActionListener(e -> dispose());

        painelBotoes.add(btnEditar);
        painelBotoes.add(btnVoltar);

        add(painelBotoes, BorderLayout.SOUTH);
    }

    private class BotaoEditarHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            int linha = tabela.getSelectedRow();

            if (linha == -1) {
                JOptionPane.showMessageDialog(null, "Selecione um produto!");
                return;
            }

            int id = (int) tabela.getValueAt(linha, 0);

            Produto p = produtosService.consultarProduto(id);

            new EditarProdutoView(produtosService, p);
        }
    }
}
