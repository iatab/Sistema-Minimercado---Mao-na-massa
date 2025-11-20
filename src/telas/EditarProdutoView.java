package telas;


import produtos.Produto;
import produtos.ProdutosService;

import javax.swing.*;
import java.awt.*;

public class EditarProdutoView extends JFrame {

    private ProdutosService produtosService;
    private Produto produto;
    private ListaProdutosView listaView;

    private JTextField tfPreco;

    public EditarProdutoView(ProdutosService produtosService, Produto produto,  ListaProdutosView listaView) {
        this.produtosService = produtosService;
        this.produto = produto;
        this.listaView = listaView;

        setTitle("Detalhes do Produto");
        setLayout(new FlowLayout());

        addLabel("ID: " + produto.getId());
        addLabel("Nome: " + produto.getNome());
        addLabel("Código de Barras: " + produto.getCodigoBarras());
        addLabel("Estoque: " + produto.getEstoque());

        addLabel("Preço atual:");
        tfPreco = new JTextField(String.valueOf(produto.getPreco()));
        tfPreco.setPreferredSize(new Dimension(200, 30));
        add(tfPreco);

        JButton btnSalvar = new JButton("Salvar Novo Preço");
        JButton btnExcluir = new JButton("Excluir Produto");
        JButton btnVoltar = new JButton("Voltar");

        btnSalvar.addActionListener(e -> salvarPreco());
        btnExcluir.addActionListener(e -> excluirProduto());
        btnVoltar.addActionListener(e -> dispose());

        add(btnSalvar);
        add(btnExcluir);
        add(btnVoltar);

        setSize(350, 350);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    private void addLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setPreferredSize(new Dimension(300, 30));
        add(label);
    }

    private void salvarPreco() {
        try {
            double novoPreco = Double.parseDouble(tfPreco.getText());

            produtosService.alterarPrecoProduto(produto.getId(), novoPreco);

            JOptionPane.showMessageDialog(null, "Preço atualizado!");

            if (listaView != null) {
                listaView.atualizarTabela();
            }

            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Preço inválido!");
        }
    }


    private void excluirProduto() {
        int op = JOptionPane.showConfirmDialog(null,
                "Tem certeza que deseja excluir?",
                "Confirmar",
                JOptionPane.YES_NO_OPTION);

        if (op == JOptionPane.YES_OPTION) {
            produtosService.excluirProduto(produto.getId());
            JOptionPane.showMessageDialog(null, "Produto removido!");
            if (listaView != null) {
                listaView.atualizarTabela();
            }
            dispose();
        }
    }
}
