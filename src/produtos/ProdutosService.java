package produtos;

import java.util.List;

public class ProdutosService {

    private Estoque estoque = new Estoque();

    // fazer um metodo mostrar registro onde tenha que mandar o
    // cliente que tenha perfil gesto, para listar os movimentos

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

    public int gerarNovoID(){
       return this.estoque.gerarNovoId();
    }


    public void listarProdutoPorId(int id){

        try {

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
        catch (IllegalArgumentException e ){
            System.out.println(e.getMessage());
        }







    }

    public void alterarPrecoProduto(int id, double valor){
        try{
            this.estoque.alterarPrecoProduto(id,valor);


        } catch (IllegalArgumentException e ){
            System.out.println(e.getMessage());
        }

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


public List<Produto> listarTodosProdutos(){

        return this.estoque.listarTodos();
}

}
