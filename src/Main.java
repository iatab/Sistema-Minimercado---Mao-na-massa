
import produtos.ProdutosService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ProdutosService produtosService = new ProdutosService();
        produtosService.criarProduto(1, "Arroz Branco Tipo 1", "7891234560011", 8.99, 6.50, 30);
        produtosService.criarProduto(2, "Feijão Carioca 1kg", "7891234560028", 6.49, 4.20, 25);
        produtosService.criarProduto(3, "Macarrão Espaguete 500g", "7891234560035", 4.29, 2.80, 40);



        produtosService.listarProdutos();

        produtosService.aumentarEstoqueProduto(2,35);
        produtosService.diminuirEstoqueProduto(2,35);
    }
}