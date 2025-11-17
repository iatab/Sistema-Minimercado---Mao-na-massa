package telas;


import clientes.ClienteService;
import produtos.ProdutosService;
import vendas.IVendaService;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuInicialView extends JFrame {

    private JPanel painel;
    private ProdutosService produtosService;



    public MenuInicialView(ProdutosService produtosService, IVendaService vendaService, ClienteService clienteService) {
        this.produtosService = produtosService;
        setTitle("Menu Inicial");
        setLayout(new FlowLayout());

        this.painel = new JPanel();
        this.painel.setLayout(new FlowLayout());
        this.painel.setPreferredSize(new Dimension(500, 700));
        add(this.painel);

//        criarBotao("Cadastrar", new BotaoCadastrarHandler());

        criarBotao("Cadastrar Produto", new BotaoCadastrarProdutoHandler(produtosService));
//
        criarBotao("Visualizar / Editar Produtos", new BotaoListarProdutosHandler(produtosService));

        criarBotao("Sair", new BotaoSairHandler());

        setSize(new Dimension(500, 500));
        setPreferredSize(new Dimension(500, 700));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    private void criarBotao(String label, ActionListener listener) {
        JButton botao = new JButton(label);
        botao.addActionListener(listener);
        botao.setPreferredSize(new Dimension(300, 80));
        this.painel.add(botao);
    }


//    private static class BotaoCadastrarHandler implements ActionListener {
//
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//          System.out.println("cliquei");
//           new CadastroProduto();
//        }
//    }
    private static class BotaoCadastrarProdutoHandler implements ActionListener {

        private ProdutosService produtosService;
        public BotaoCadastrarProdutoHandler(ProdutosService produtoService) {
            this.produtosService = produtoService;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
//            System.out.println("cliquei");
           new CadastroProduto(produtosService);
        }
    }

    private static class BotaoListarProdutosHandler implements  ActionListener{

        private ProdutosService produtosService;
        public BotaoListarProdutosHandler(ProdutosService produtosService) {
            this.produtosService = produtosService;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            new ListaProdutosView(produtosService);

        }
    }





    private static class BotaoSairHandler implements  ActionListener{


        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);

        }
    }

}
