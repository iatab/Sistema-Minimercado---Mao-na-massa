package telas;

import produtos.Produto;
import produtos.ProdutosService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroProduto extends JFrame {

    private JPanel painel;
    private JTextField tfNome;
    private JTextField tfCodigoBarras;
    private JTextField tfPreco;
    private JTextField tfEstoque;

    private ProdutosService produtosService;

    public CadastroProduto(ProdutosService produtosService) {

        this.produtosService = produtosService;

        setTitle("Cadastro de Produto");
        setLayout(new FlowLayout());

        this.painel = new JPanel();
        this.painel.setLayout(new FlowLayout());
        this.painel.setPreferredSize(new Dimension(500, 900));
        add(painel);

        // Campos
        this.tfNome = criarTextField("Nome");
        this.tfCodigoBarras = criarTextField("Código de Barras");
        this.tfPreco = criarTextField("Preço");
        this.tfEstoque = criarTextField("Estoque");

        criarBotao("Salvar", new BotaoSalvarHandler());
        criarBotao("Voltar", new BotaoVoltarHandler());

        setSize(500, 700);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private JTextField criarTextField(String labelTexto) {
        JLabel label = new JLabel(labelTexto);
        label.setPreferredSize(new Dimension(400, 40));
        painel.add(label);

        JTextField campo = new JTextField();
        campo.setPreferredSize(new Dimension(400, 40));
        painel.add(campo);

        return campo;
    }

    private void criarBotao(String label, ActionListener listener) {
        JButton botao = new JButton(label);
        botao.addActionListener(listener);
        botao.setPreferredSize(new Dimension(300, 80));
        painel.add(botao);
    }

    private class BotaoVoltarHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
        }
    }

    private void criarItem() {

        String nome = tfNome.getText().trim();
        String codigo = tfCodigoBarras.getText().trim();
        String precoTxt = tfPreco.getText().trim();
        String estoqueTxt = tfEstoque.getText().trim();

        // === VALIDAÇÕES ===

        // Campos vazios
        if (nome.isEmpty() || codigo.isEmpty() || precoTxt.isEmpty() || estoqueTxt.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
            return;
        }

        // Nome repetido
        if (produtosService.nomeExiste(nome)) {
            JOptionPane.showMessageDialog(null, "Já existe um produto com esse nome!");
            return;
        }

        // Código repetido
        if (produtosService.codigoExiste(codigo)) {
            JOptionPane.showMessageDialog(null, "Já existe um produto com esse código de barras!");
            return;
        }

        // Somente números
        if (!codigo.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "O código de barras deve conter apenas números!");
            return;
        }

        // Tamanho válido: 13 ou 14 dígitos
        if (!(codigo.length() == 13 )) {
            JOptionPane.showMessageDialog(null,
                    "Código de barras inválido!\n" +
                            "Use 13 dígitos.");
            return;
        }

        // Preço válido
        double preco;
        try {
            preco = Double.parseDouble(precoTxt.replace(",", "."));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Digite um preço válido!");
            return;
        }

        // Estoque válido
        int estoque;
        try {
            estoque = Integer.parseInt(estoqueTxt);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Digite um valor de estoque válido!");
            return;
        }

        // Criar produto
        Produto produto = new Produto(
                nome,
                codigo,
                preco,
                0.0,
                estoque
        );

        produtosService.cadastrarProduto(produto);

        JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
    }

    private class BotaoSalvarHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            criarItem();
        }
    }
}
