package produtos;

public class ProdutosService {

    private Estoque estoque = new Estoque();
    // falta implementar um sistema de registro


    public void criarProduto(int id, String nome, String codigoBarras, double preco, double custoMedio, int estoque){
    Produto p = new Produto(id,nome,codigoBarras,preco,custoMedio,estoque);
    this.estoque.adicionarProduto(p);



    }

    public void aumentarEstoqueProduto(int id, int valor){
    this.estoque.aumentarEstoqueProduto(id,valor);
    }

    public void diminuirEstoqueProduto(int id, int valor){
    this.estoque.diminuirEstoqueProduto(id,valor);

    }



    public void consultarProduto(int id){
        Produto p = this.estoque.consultarProdutoPorId(id);
        System.out.println("Nome: " + p.getNome());
        System.out.println("Preco: " + p.getPreco());
        System.out.println("Quantidade: " + p.getEstoque());

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
