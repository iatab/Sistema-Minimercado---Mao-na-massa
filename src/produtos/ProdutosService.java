package produtos;

public class ProdutosService {

    private Estoque estoque = new Estoque();



    public void cadastrarProduto(Produto produto){

    this.estoque.adicionarProduto(produto);



    }

    public void aumentarEstoqueProduto(int id, int valor){
    this.estoque.aumentarEstoqueProduto(id,valor);
    }

    public void diminuirEstoqueProduto(int id, int valor){
    this.estoque.diminuirEstoqueProduto(id,valor);

    }



    public Produto consultarProduto(int id){
        Produto p = this.estoque.consultarProdutoPorId(id);
        return  p;

    }

    public void listarProdutoPorId(int id){
        Produto p = this.estoque.consultarProdutoPorId(id);
        System.out.println("-----------------------------------------");
        System.out.println("RELATÓRIO DE UM PRODUTO NO ESTOQUE");
        System.out.println("Id: " + p.getId());
        System.out.println("nome: " + p.getNome());
        System.out.println("Quantidade no estoque: " + p.getEstoque());
        System.out.println("Preço " + p.getPreco());
        System.out.println("Custo Médio: " + p.getCustoMedio());
        System.out.println("Codigo de Barras: " + p.getCodigoBarras());
        System.out.println("-----------------------------------------");


    }

    public void listarProdutos(){
    this.estoque.listarProdutos();

    }

    public void removerProduto(int id){
        this.estoque.removerProduto(id);


    }

    public void listarRegistro(){
        estoque.listarRegistros();
    }



}
