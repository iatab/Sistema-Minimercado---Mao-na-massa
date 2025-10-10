package produtos;

import java.util.ArrayList;

public class Estoque {

    private ArrayList<Produto> produtos = new ArrayList<>();


    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }





    public void adicionarProduto(Produto p){
        produtos.add(p);
        System.out.println("produto adicionado!");
    }

    public void aumentarEstoqueProduto(int id, int valor){

        for (Produto p : produtos) {
            if(p.getId() == id) {
                System.out.println("quantidade antiga: " + p.getEstoque());
                int estoque = p.getEstoque();
                p.setEstoque( estoque + valor);
                System.out.println("quantidade nova: " + p.getEstoque());
            }
        }

    }

    public void diminuirEstoqueProduto(int id, int valor){

        for (Produto p : produtos) {
            if(p.getId() == id) {
                System.out.println("-------------------------------");
                System.out.println("quantidade antiga: " + p.getEstoque());
                int estoque = p.getEstoque();
                p.setEstoque(estoque - valor);
                System.out.println("-------------------------------");
                System.out.println("quantidade nova: " + p.getEstoque());
                System.out.println("-------------------------------");
            }
        }


    }



    public Produto consultarProdutoPorId(int id){
        for (Produto p : produtos) {
            if(p.getId() == id) {
                return p;
            }
        } return null;



    }

    public void listarProdutos(){
    for (Produto p : produtos) {
        System.out.println("-------------------------------");
        System.out.println("Nome: " + p.getNome());
        System.out.println("-------------------------------");
    }

    }

    public void removerProduto(int id) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId() == id) {
                produtos.remove(i);
                System.out.println("-------------------------------");
                System.out.println("Produto removido com sucesso!");
                System.out.println("-------------------------------");
                return;
            }
        }
        System.out.println("-------------------------------");
        System.out.println("Produto não encontrado.");
        System.out.println("-------------------------------");
    }



}
