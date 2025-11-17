
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

        // agora os campos são criados corretamente e armazenados
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

        try {
            String nome = tfNome.getText();
            String codigo = tfCodigoBarras.getText();

            double preco = Double.parseDouble(tfPreco.getText());
            int estoque = Integer.parseInt(tfEstoque.getText());

            Produto produto = new Produto(
                    1,    // depois você troca pelo gerador de ID que quiser
                    nome,
                    codigo,
                    preco,
                    0.0, // custo médio (preenche depois)
                    estoque
            );

            produtosService.cadastrarProduto(produto);

            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Erro: digite valores numéricos válidos!");
        }
    }

    private class BotaoSalvarHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            criarItem();
        }
    }
}






















//package telas;
//
//import produtos.Produto;
//import produtos.ProdutosService;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class CadastroProduto extends JFrame {
//
//
//    private JPanel painel;
//    private JTextField tfNome;
//    private JTextField tfCodigoBarras;
//    private JTextField tfPreco;
//    private JTextField tfEstoque;
//
//    public CadastroProduto(ProdutosService produtosService) {
//        setTitle("Cadastro de Itens");
//        setLayout(new FlowLayout());
//
//        this.painel = new JPanel();
//        this.painel.setLayout(new FlowLayout());
//        this.painel.setPreferredSize(new Dimension(500, 500));
//        add(painel);
//
//
//        criarTextField1("Nome", this.tfNome);
//        criarTextField2("Codigo de Barras", this.tfCodigoBarras);
//        criarTextField3("Preço", this.tfPreco);
//        criarTextField4("Estoque", this.tfEstoque);
//
//
//        criarBotao("salvar", new BotaoSalvarHandler());
//
//        //incluir codigos
//
//        criarBotao("Voltar", new BotaoVoltarHandler());
//
//        setSize(new Dimension(500, 500));
//        setPreferredSize(new Dimension(500,500));
//        setVisible(true);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//    }
//
//    private void criarLabel (String texto){
//        JLabel label = new JLabel(texto);
//        label.setPreferredSize(new Dimension(400,40));
//        this.painel.add(label);
//    }
//
//    private void criarTextField(String texto, JTextField caixaTexto){
//        criarLabel(texto);
//        caixaTexto = new JTextField();
//        caixaTexto.setPreferredSize(new Dimension(400,40));
//        this.painel.add(caixaTexto);
//        //tem que criar 3x para cada caixa de texto
//    }
//
//    private void criarTextField1(String texto, JTextField caixaTexto){
//        criarLabel(texto);
//        caixaTexto = new JTextField();
//        caixaTexto.setPreferredSize(new Dimension(400,40));
//        this.painel.add(caixaTexto);
//        //tem que criar 3x para cada caixa de texto
//    }
//    private void criarTextField2(String texto, JTextField caixaTexto){
//        criarLabel(texto);
//        caixaTexto = new JTextField();
//        caixaTexto.setPreferredSize(new Dimension(400,40));
//        this.painel.add(caixaTexto);
//        //tem que criar 3x para cada caixa de texto
//    }
//
//    private void criarTextField3(String texto, JTextField caixaTexto){
//        criarLabel(texto);
//        caixaTexto = new JTextField();
//        caixaTexto.setPreferredSize(new Dimension(400,40));
//        this.painel.add(caixaTexto);
//        //tem que criar 3x para cada caixa de texto
//    }
//
//    private void criarTextField4(String texto, JTextField caixaTexto){
//        criarLabel(texto);
//        caixaTexto = new JTextField();
//        caixaTexto.setPreferredSize(new Dimension(400,40));
//        this.painel.add(caixaTexto);
//        //tem que criar 3x para cada caixa de texto
//    }
//
//
//
//
//    private void criarBotao(String label, ActionListener listener) {
//        JButton botao = new JButton(label);
//        botao.addActionListener(listener);
//        botao.setPreferredSize(new Dimension(300, 80));
//        this.painel.add(botao);
//    }
//
//
//    private  class BotaoVoltarHandler implements ActionListener {
//
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
////            System.out.println("cliquei");
//            setVisible(false);
//        }
//    }
//    private void criarItem() {
////            String ano = this.tfAno.getText(); // para pegar texto
//        // item item = new item(nome, ano, valor);
//        //this.service.inseriritem(..);
//        String Nome = this.tfNome.getText();
//        String CodigoBarras = this.tfCodigoBarras.getText();
//        double Preco = this.tfPreco.getText();
//        int Estoque = this.tfEstoque.getText();
//        private Produto produto = new Produto(1,Nome,CodigoBarras,Preco,null,Estoque);
//
//
//        JOptionPane.showMessageDialog(null, "Cadastro salvo com sucesso!");
//    }
//
//    private  class BotaoSalvarHandler implements ActionListener {
//
//        private ProdutosService produtosService;
//
//        public BotaoSalvarHandler (ProdutosService produtosService){
//            this.produtosService = produtosService;
//        }
//
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//
////            setVisible(false);
//
//            criarItem(produtosService);
//
//        }
//    }
//
//
//}
