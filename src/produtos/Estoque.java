package produtos;

import java.util.ArrayList;

public class Estoque {

    private ArrayList<Produto> produtos = new ArrayList<>();
    private RegistroService registro = new RegistroService();


    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }





    public void adicionarProduto(Produto p){
        produtos.add(p);
        System.out.println("produto adicionado!");
        registro.registrarEntrada(p.getId(),p.getEstoque(),"foram adicionados: "+ p.getEstoque() + " " + p.getNome());
    }

    public void aumentarEstoqueProduto(int id, int valor){

        for (Produto p : produtos) {
            if(p.getId() == id) {
                int estoque = p.getEstoque();
                p.setEstoque( estoque + valor);
                registro.registrarEntrada(p.getId(),p.getEstoque(),"foram adicionados: " + valor + " " +p.getNome()+ " ao estoque");
                System.out.println("-------------------------------");
                registro.listarUltimoMovimento();
                System.out.println("-------------------------------");
            }
        }

    }

    public void diminuirEstoqueProduto(int id, int valor){

        for (Produto p : produtos) {
            if(p.getId() == id) {
                int estoque = p.getEstoque();
                p.setEstoque(estoque - valor);
                registro.registrarSaida(p.getId(),p.getEstoque(),"foram removidos: " + valor + " " +p.getNome()+ " ao estoque");
                System.out.println("-------------------------------");
                registro.listarUltimoMovimento();
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
        System.out.println("Nome: " + p.getNome() + " quantidade: "+p.getEstoque());
        System.out.println("-------------------------------");
    }

    }

    public void removerProduto(int id) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId() == id) {
                registro.registrarBaixa(id,produtos.get(i).getEstoque(),produtos.get(i).getNome()+" removido com sucesso");
                System.out.println("-------------------------------");
                registro.listarUltimoMovimento();
                System.out.println("-------------------------------");
                produtos.remove(i);

                return;
            }
        }
        System.out.println("-------------------------------");
        System.out.println("Produto não encontrado.");
        System.out.println("-------------------------------");
    }

    public void listarRegistros(){
        registro.listarMovimentos();
    }



}
