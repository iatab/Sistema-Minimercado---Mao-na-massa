
import produtos.Produto;
import produtos.ProdutosService;
import vendas.IVendaService;
import vendas.Venda;
import vendas.VendaService;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ProdutosService produtosService = new ProdutosService();
        IVendaService vendaService = new VendaService(produtosService);


      popularProdutos(produtosService);
     testarVendas(vendaService, produtosService);
      testarProdutos(produtosService);


        // posso criar uma venda sem itens, porém para finaliza-la tenho que ter itens adicionados. para isso verifico no metodo finalizar venda com um isEmpty
        // posso adicionar o objeto produto ao metodo adicionar item ao estoque junto com a quantidade de itens que quero adicionar ao pedido. ai verifica no estoque

//        ItensVenda itemVenda = new ItensVenda(1,p1.getNome(),3,p1.getPreco());

//        Venda venda = new Venda(1);

//        vendas.iniciarVenda(venda);










    }




    public static void testarVendas(IVendaService vendaService, ProdutosService produtosService){


        // falta colocar o cliente e fazer fidelidade
        //ideia : fazer um metodo adicionar cliente e aplicar desconto, onde se le o tipo de perfil e aplica o desconto corresponde ao perfil
        System.out.println("TESTANDO O SISTEMA DE VENDAS ");
        Venda v1 = new Venda(1);
        vendaService.iniciarVenda(v1);
        vendaService.adicionarItemDaVenda(1,1, 10);
        vendaService.adicionarItemDaVenda(2,1, 10);

        Venda v2 = new Venda(2);
        vendaService.iniciarVenda(v2);
        vendaService.adicionarItemDaVenda(2,2,10);
        vendaService.listarVendas();


    }



    public static void popularProdutos(ProdutosService produtosService){


        System.out.println("POPULANDO DE PRODUTOS NO ESTOQUE");
        Produto p1 = new Produto(1, "Arroz Branco Tipo 1", "7891234560011", 8.99, 6.50, 30);
        Produto p2 = new Produto(2, "Feijão Carioca 1kg", "7891234560028", 6.49, 4.20, 25);
        Produto p3 = new Produto(3, "Macarrão Espaguete 500g", "7891234560035", 4.29, 2.80, 40);

        produtosService.cadastrarProduto(p1);
        produtosService.cadastrarProduto(p2);
        produtosService.cadastrarProduto(p3);




    }


    public static void testarProdutos(ProdutosService produtosService){

        System.out.println("TESTANDO O PRODUTOSERVICE");
        //teste de produto service


        produtosService.listarProdutos();
        produtosService.listarProdutoPorId(1);

        produtosService.aumentarEstoqueProduto(2,35);
        produtosService.diminuirEstoqueProduto(2,35);

        produtosService.removerProduto(3);

        produtosService.listarProdutos();

       produtosService.listarRegistro();

    }



}