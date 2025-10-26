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
        Produto produtoEncontrado = null ;

        for (Produto p : produtos) {
            if (p.getId() == id) {
                produtoEncontrado = p;
                break;
            }
        }
        if (produtoEncontrado == null) {
            System.out.println(" Nao é possivel aumentar o estoque de um produto que nao existe ");
        }

        if (valor <= 0) {
            System.out.println("Erro: o valor para aumento de estoque deve ser positivo.");
            return;
        }

        for (Produto p : produtos) {
            if(p.getId() == id) {
                int estoque = p.getEstoque();
                if(estoque + valor < 0 ) {
                    System.out.println("Operacao ilegal o estoque nao pode ficar negativo");
                } else {

                    p.setEstoque( estoque + valor);
                    registro.registrarEntrada(p.getId(),p.getEstoque(),"foram adicionados: " + valor + " " +p.getNome()+ " ao estoque");
                    System.out.println("-------------------------------");
                    registro.listarUltimoMovimento();
                    System.out.println("-------------------------------");
                }

            }
        }

    }

    public void diminuirEstoqueProduto(int id, int valor){

        Produto produtoEncontrado = null ;

        for (Produto p : produtos) {
            if (p.getId() == id) {
                produtoEncontrado = p;
                break;
            }
        }
        if (produtoEncontrado == null) {
            System.out.println(" Nao é possivel diminuir o estoque de um produto que nao existe ");
        }


        for (Produto p : produtos) {
            if(p.getId() == id) {
                int estoque = p.getEstoque();
                if(estoque - valor < 0 ) {
                    System.out.println("Operacao ilegal o estoque nao pode ficar negativo");
                } else {

                    p.setEstoque(estoque - valor);
                    registro.registrarSaida(p.getId(),p.getEstoque(),"foram removidos: " + valor + " " +p.getNome()+ " do estoque");
                    System.out.println("-------------------------------");
                    registro.listarUltimoMovimento();
                    System.out.println("-------------------------------");
                }


            }
        }


    }



    public Produto consultarProdutoPorId(int id){
        for (Produto p : produtos) {
            if(p.getId() == id) {
                return p;
            }
        } throw new IllegalArgumentException("Produto com ID " + id + " não encontrado.");



    }

    public void alterarPrecoProduto(int id, int valor){
        Produto produto = this.consultarProdutoPorId(id) ;
        produto.setPreco(valor);



//        for (Produto p : produtos) {
//            if(p.getId() == id) {
//
//
//
//            }
//        }
    }

    public void listarProdutos(){
        System.out.println("-------------------------------");
        System.out.println("LISTA DE TODOS PRODUTOS NO ESTOQUE");
    for (Produto p : produtos) {

        System.out.println("Nome: " + p.getNome() + " quantidade: "+p.getEstoque()+ " Preço: " + p.getPreco()) ;

    }
        System.out.println("-------------------------------");

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
