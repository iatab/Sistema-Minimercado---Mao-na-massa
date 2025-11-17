package telas;



import produtos.Produto;
import produtos.ProdutosService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlterarPrecoView extends JFrame {

    private ProdutosService produtosService;

    private JTextField campoId;
    private JTextField campoNome;
    private JTextField campoPreco;

    private JButton botaoCarregar;
    private JButton botaoSalvar;
    private JButton botaoVoltar;

    private Produto produtoCarregado;

    public AlterarPrecoView(ProdutosService produtosService) {
        this.produtosService = produtosService;

        setTitle("Editar Preço do Produto");
        setSize(500, 600);
        setLayout(new GridLayout(5, 2, 10, 10));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Componentes
        add(new JLabel("ID do Produto:"));
        campoId = new JTextField();
        add(campoId);

        botaoCarregar = new JButton("Carregar Produto");
        add(botaoCarregar);

        // Linha vazia pra alinhar
        add(new JLabel(""));

        add(new JLabel("Nome:"));
        campoNome = new JTextField();
        campoNome.setEditable(false);
        add(campoNome);

        add(new JLabel("Preço Atual:"));
        campoPreco = new JTextField();
        campoPreco.setEnabled(false);
        add(campoPreco);

        botaoSalvar = new JButton("Salvar Novo Preço");
        botaoSalvar.setEnabled(false);
        add(botaoSalvar);

        botaoVoltar = new JButton("Voltar");
        add(botaoVoltar);

        // Ações
        configurarAcoes();

        setVisible(true);
    }

    private void configurarAcoes() {

        // Carregar produto pelo ID
        botaoCarregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    int id = Integer.parseInt(campoId.getText());

                    produtoCarregado = produtosService.consultarProduto(id);

                    if (produtoCarregado == null) {
                        JOptionPane.showMessageDialog(null,
                                "Produto não encontrado!",
                                "Erro",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    campoNome.setText(produtoCarregado.getNome());
                    campoPreco.setText(String.valueOf(produtoCarregado.getPreco()));
                    campoPreco.setEnabled(true);
                    botaoSalvar.setEnabled(true);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,
                            "ID inválido!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Salvar alteração de preço
        botaoSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    double novoPreco = Double.parseDouble(campoPreco.getText());

                    produtoCarregado.setPreco(novoPreco);
                    produtosService.alterarPrecoProduto(produtoCarregado.getId(),novoPreco);

                    JOptionPane.showMessageDialog(null,
                            "Preço atualizado com sucesso!");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Preço inválido!",
                            "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Voltar
        botaoVoltar.addActionListener(e -> dispose());
    }
}

