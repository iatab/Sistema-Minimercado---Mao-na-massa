package telas;

import produtos.Produto;
import produtos.ProdutosService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RelatorioCatalogoView extends JFrame {

    public RelatorioCatalogoView(ProdutosService service) {

        setTitle("Catálogo de Produtos");
        setSize(600, 400);
        setLayout(new BorderLayout());

        String[] colunas = {"ID", "Nome", "Preço"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        for (Produto p : service.listarTodosProdutos()) {
            model.addRow(new Object[]{
                    p.getId(),
                    p.getNome(),
                    p.getPreco()
            });
        }

        JTable tabela = new JTable(model);
        tabela.setEnabled(false);

        add(new JScrollPane(tabela), BorderLayout.CENTER);

        JButton voltar = new JButton("Voltar");
        voltar.addActionListener(e -> dispose());

        JPanel baixo = new JPanel();
        baixo.add(voltar);

        add(baixo, BorderLayout.SOUTH);

        setVisible(true);
    }
}
