import clientes.*;
import produtos.Produto;
import produtos.ProdutosService;
import vendas.IVendaService;
import vendas.Venda;
import vendas.VendaService;

public class Main {
    public static void main(String[] args) {

        // Instancia os serviços principais
        ProdutosService produtosService = new ProdutosService();
        IVendaService vendaService = new VendaService(produtosService);
        ClienteService clienteService = new ClienteService();

        System.out.println("===== INICIANDO SISTEMA DE TESTES =====");

        // Popular dados iniciais
        popularProdutos(produtosService);
        popularClientes(clienteService);

        // Testes separados
        testarProdutos(produtosService);
        testarVendas(vendaService, produtosService, clienteService);

        System.out.println("===== FIM DOS TESTES =====");
    }

    // ============================
    //  MÉTODOS DE POPULAÇÃO
    // ============================

    public static void popularProdutos(ProdutosService produtosService) {
        System.out.println("\n POPULANDO PRODUTOS NO ESTOQUE");

        Produto p1 = new Produto(1, "Arroz Branco Tipo 1", "7891234560011", 8.99, 6.50, 300);
        Produto p2 = new Produto(2, "Feijão Carioca 1kg", "7891234560028", 6.49, 4.20, 250);
        Produto p3 = new Produto(3, "Macarrão Espaguete 500g", "7891234560035", 4.29, 2.80, 400);

        produtosService.cadastrarProduto(p1);
        produtosService.cadastrarProduto(p2);
        produtosService.cadastrarProduto(p3);

        produtosService.listarProdutos();
    }

    public static void popularClientes(ClienteService clienteService) {
        System.out.println("\n CADASTRANDO CLIENTES");

        clienteService.adicionarCliente(new ClientePessoaFisica(
                1, "João Silva", "123.456.789-00", "99999-9999", Categoria.OURO));

        clienteService.adicionarCliente(new ClientePessoaFisica(
                2, "Maria Souza", "987.654.321-00", "98888-8888", Categoria.PRATA));

        clienteService.adicionarCliente(new ClientePessoaJuridica(
                3, "Tech Solutions LTDA", "12.345.678/0001-90", "97777-7777", Categoria.PLATINA));

        clienteService.adicionarCliente(new ClientePessoaJuridica(
                4, "Comercial Silva ME", "98.765.432/0001-10", "96666-6666", Categoria.BRONZE));

        clienteService.listarClientes();
    }

    // ============================
    //  TESTES DE PRODUTOS
    // ============================

    public static void testarProdutos(ProdutosService produtosService) {
        System.out.println("\n TESTANDO PRODUTOSERVICE");

        produtosService.listarProdutos();
        produtosService.listarProdutoPorId(1);

        System.out.println("TESTE DE AUMENTAR E DIMINUIR ESTOQUE");
        produtosService.aumentarEstoqueProduto(2, 35);
        produtosService.diminuirEstoqueProduto(2, 20);

        System.out.println("TESTE DE PRODUTO NAO ENCONTRADO (AUMENTAR E DIMINUIR ESTOQUE)");
        produtosService.aumentarEstoqueProduto(25, 35);
        produtosService.diminuirEstoqueProduto(25, 20);
        System.out.println("--------------------");
        System.out.println("ALTERAR PRECO DE UM PRODUTO");
        produtosService.alterarPrecoProduto(1, 20);
        System.out.println("--------------------");

        System.out.println("ALTERAR PRECO DE PRODUTO INEXISTENTE");
        produtosService.alterarPrecoProduto(50, 20);
        System.out.println("--------------------");

        System.out.println("REMOVER PRODUTO 3");
        produtosService.removerProduto(3);
        System.out.println("REMOVER UM PRODUTO INEXISTENTE");
        produtosService.removerProduto(35); // teste produto inexistente

        produtosService.listarProdutos();
        produtosService.listarRegistro();
    }

    // ============================
    //  TESTES DE VENDAS
    // ============================

    public static void testarVendas(IVendaService vendaService,
                                    ProdutosService produtosService,
                                    ClienteService clienteService) {

        System.out.println("\n TESTANDO SISTEMA DE VENDAS");

        // Venda sem cliente
        Venda v1 = new Venda(1);
        vendaService.iniciarVenda(v1);
        vendaService.adicionarItemDaVenda(1, 1, 10);
        vendaService.adicionarItemDaVenda(2, 1, 5);

        vendaService.removerItemDaVenda(1, 1);


        // Venda com cliente PF
        Cliente cliente1 = clienteService.buscarPorId(1);
        Venda v2 = new Venda(2, cliente1);
        vendaService.iniciarVenda(v2);
        vendaService.adicionarItemDaVenda(2, 2, 10);
        vendaService.aplicarDesconto(2);

        // Venda com cliente PJ
        Cliente cliente3 = clienteService.buscarPorId(3);
        Venda v3 = new Venda(3, cliente3);
        vendaService.iniciarVenda(v3);
        vendaService.adicionarItemDaVenda(1, 3, 20);
        vendaService.adicionarItemDaVenda(2, 3, 15);
        vendaService.aplicarDesconto(3);

        System.out.println("\n LISTAGEM FINAL DE VENDAS:");
        vendaService.listarVendas();
    }
}











































// OLD MAIN

//
//import clientes.*;
//import produtos.Produto;
//import produtos.ProdutosService;
//import vendas.IVendaService;
//import vendas.Venda;
//import vendas.VendaService;
//
//
////TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
//// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
//public class Main {
//    public static void main(String[] args) {
//        ProdutosService produtosService = new ProdutosService();
//        IVendaService vendaService = new VendaService(produtosService);
//        ClienteService clienteService = new ClienteService();
//
//      popularProdutos(produtosService);
////      popularClientes(clienteService);
////     testarVendas(vendaService, produtosService, clienteService);
//      testarProdutos(produtosService);
//
//
//        // posso criar uma venda sem itens, porém para finaliza-la tenho que ter itens adicionados. para isso verifico no metodo finalizar venda com um isEmpty
//        // posso adicionar o objeto produto ao metodo adicionar item ao estoque junto com a quantidade de itens que quero adicionar ao pedido. ai verifica no estoque
//
////        ItensVenda itemVenda = new ItensVenda(1,p1.getNome(),3,p1.getPreco());
//
////        Venda venda = new Venda(1);
//
////        vendas.iniciarVenda(venda);
//
//
//
//
//
//
//
//
//
//
//    }
//
//
//    public static void popularClientes (ClienteService clienteService) {
//        ClientePessoaFisica cliente1 = new ClientePessoaFisica(
//                1,
//                "João Silva",
//                "123.456.789-00",
//                "99999-9999",
//                Categoria.OURO
//        );
//
//        ClientePessoaFisica cliente2 = new ClientePessoaFisica(
//                2,
//                "Maria Souza",
//                "987.654.321-00",
//                "98888-8888",
//                Categoria.OURO
//        );
//
//        // Criando clientes pessoa jurídica
//        ClientePessoaJuridica cliente3 = new ClientePessoaJuridica(
//                3,
//                "Tech Solutions LTDA",
//                "12.345.678/0001-90",
//                "97777-7777",
//                Categoria.PLATINA
//        );
//
//        ClientePessoaJuridica cliente4 = new ClientePessoaJuridica(
//                4,
//                "Comercial Silva ME",
//                "98.765.432/0001-10",
//                "96666-6666",
//                Categoria.BRONZE
//        );
//
//        clienteService.adicionarCliente(cliente1);
//        clienteService.adicionarCliente(cliente2);
//        clienteService.adicionarCliente(cliente3);
//        clienteService.adicionarCliente(cliente4);
//
//
//
//    }
//
//
//
//
//    public static void testarVendas(IVendaService vendaService, ProdutosService produtosService, ClienteService clienteService) {
//
//
//        // falta colocar o cliente e fazer fidelidade
//        //ideia : fazer um metodo adicionar cliente e aplicar desconto, onde se le o tipo de perfil e aplica o desconto corresponde ao perfil
//        System.out.println("TESTANDO O SISTEMA DE VENDAS ");
//        Venda v1 = new Venda(1);
//        vendaService.iniciarVenda(v1);
//        vendaService.adicionarItemDaVenda(1,1, 10);
//        vendaService.adicionarItemDaVenda(2,1, 10);
//        vendaService.removerItemDaVenda(1,1);
//
//        Venda v2 = new Venda(2);
//        vendaService.iniciarVenda(v2);
//        vendaService.adicionarItemDaVenda(2,2,10);
//        vendaService.aplicarDesconto(2);
////        vendaService.listarVendas();
//        System.out.println("VENDAS COM CLIENTES ");
//        // verificar o remover item venda
//        // cliente pessoa fisica
//        Cliente cliente1 = clienteService.buscarPorId(1); // adiciono cliente previamente cadastrado
//        Venda v3 = new Venda(3,cliente1); // crio a venda
//        vendaService.iniciarVenda(v3);// inicio a venda
//        vendaService.adicionarItemDaVenda(1,3,2); // adiciono item a venda
//        vendaService.aplicarDesconto(3); // aplico o descono a venda
////        vendaService.listarVendas(); // listo as vendas
//
//        Cliente cliente2 = clienteService.buscarPorId(2);
//        Venda v4 = new Venda(4,cliente2);
//        vendaService.iniciarVenda(v4);
//        vendaService.adicionarItemDaVenda(1,4,20);
//        vendaService.adicionarItemDaVenda(2,4,20);
//        vendaService.aplicarDesconto(4);
//        vendaService.listarVendas();
//
//
//
//
//            // clientes cnpj
//        Cliente cliente3 = clienteService.buscarPorId(3);
//        Venda v5 = new Venda(5,cliente3);
//        vendaService.iniciarVenda(v5);
//        vendaService.adicionarItemDaVenda(1,5,20);
//        vendaService.adicionarItemDaVenda(3,5,20);
//        vendaService.aplicarDesconto(5);
////        vendaService.listarVendas();
//
//
//        Cliente cliente4 = clienteService.buscarPorId(4);
//        Venda v6 = new Venda(6,cliente4);
//        vendaService.iniciarVenda(v6);
//        vendaService.adicionarItemDaVenda(3,6,20);
//        vendaService.adicionarItemDaVenda(2,6,20);
//        vendaService.aplicarDesconto(6);
//        vendaService.listarVendas();
//
//
//
//    }
//
//
//
//    public static void popularProdutos(ProdutosService produtosService){
//
//
//        System.out.println("POPULANDO DE PRODUTOS NO ESTOQUE");
//        Produto p1 = new Produto(1, "Arroz Branco Tipo 1", "7891234560011", 8.99, 6.50, 300);
//        Produto p2 = new Produto(2, "Feijão Carioca 1kg", "7891234560028", 6.49, 4.20, 250);
//        Produto p3 = new Produto(3, "Macarrão Espaguete 500g", "7891234560035", 4.29, 2.80, 400);
//
//        produtosService.cadastrarProduto(p1);
//        produtosService.cadastrarProduto(p2);
//        produtosService.cadastrarProduto(p3);
//
//
//
//
//
//    }
//
//
//    public static void testarProdutos(ProdutosService produtosService){
//
//
//
//
//
//        System.out.println("TESTANDO O PRODUTOSERVICE");
//        //teste de produto service
//
//        // criar um listar produto por id
//
//
//        produtosService.listarProdutos();
//        produtosService.listarProdutoPorId(1);
//
//        produtosService.aumentarEstoqueProduto(2,35);
//        produtosService.diminuirEstoqueProduto(2,35);
//
//        produtosService.removerProduto(3);
//        produtosService.alterarPrecoProduto(1,20);
//
//        produtosService.listarProdutos();
//
//       produtosService.listarRegistro();
//
//    }
//
//
//
//}