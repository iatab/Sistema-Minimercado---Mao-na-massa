package vendas;

import produtos.Produto;
import produtos.ProdutosService;

import java.util.HashMap;
import java.util.Map;

public class VendaService implements IVendaService {

    private Map<Integer,Venda> vendas ;
    private ProdutosService produtosService;

    public VendaService(ProdutosService produtosService) {
        this.vendas = new HashMap<>();
        this.produtosService = produtosService;
    }

    @Override
    public void iniciarVenda(Venda venda) {
        // vou criar a venda no main e adicionar ao map usando o vendas service

        if(this.vendas.containsKey(venda.getId())){
            throw new VendaDuplicadaException("Id da Venda ja existe no servidor");
        }
        this.vendas.put(venda.getId(), venda);

    vendas.put(venda.getId(), venda);


    }

    @Override
    public void adicionarItemDaVenda(int idProduto, int idVenda, int quantidade) {

        if (quantidade <= 0 ) {
            System.out.println("nao é possivel adicionar uma quantidade negativa ou zerada de itens a venda ");
        } else {

            try{

                if(produtosService.consultarProduto(idProduto).getEstoque() >= quantidade) {
                    Produto produto = produtosService.consultarProduto(idProduto);
                    ItensVenda item = new ItensVenda(produto.getId(),produto.getNome(), quantidade, produto.getPreco());
                    //adiciona o item a venda
                    vendas.get(idVenda).getItens().add(item);
                    //atualiza o valor total da venda
                    vendas.get(idVenda).setValorTotal((vendas.get(idVenda).getValorTotal() + item.getPreco() * quantidade) );
                    // diminui a quantidade do estoque
                    produtosService.consultarProduto(idProduto).setEstoque(produto.getEstoque() - quantidade );

                } else {
                    System.out.println("nao ha estoque suficiente");
                }

            } catch (RuntimeException e ) {
                System.out.println(e.getMessage());
            }


        }





        // eu tenho que adicionar o produto aqui e verificar se a quantidade necessária é a mesma do estoque.



    }


    @Override
    public void removerItemDaVenda(int idProduto, int idVenda) {
        try {

            Venda venda = vendas.get(idVenda);

            if (venda == null) {
                System.out.println("Venda com ID " + idVenda + " não encontrada.");
                return;
            }

            if (venda.getItens() == null || venda.getItens().isEmpty()) {
                System.out.println("Nenhum item encontrado nessa venda.");
                return;
            }

            ItensVenda itemParaRemover = null;

            // procura o item com o id do produto
            for (ItensVenda item : venda.getItens()) {
                if (item.getId() == idProduto) {
                    itemParaRemover = item;
                    break;
                }
            }

            if (itemParaRemover != null) {
                // devolve o estoque do produto removido
                Produto produto = produtosService.consultarProduto(idProduto);
//            produto.setEstoque(produto.getEstoque() + itemParaRemover.getQuantidade());
//                produtosService.aumentarEstoqueProduto(idProduto,produto.getEstoque() + itemParaRemover.getQuantidade());
                produtosService.aumentarEstoqueProduto(idProduto, itemParaRemover.getQuantidade());

                // atualiza o valor total da venda
                double valorRemovido = itemParaRemover.getPreco() * itemParaRemover.getQuantidade();
                venda.setValorTotal(venda.getValorTotal() - valorRemovido);

                // remove o item da lista
                venda.getItens().remove(itemParaRemover);

                System.out.println("Item '" + itemParaRemover.getNome() + "' removido da venda " + idVenda);
            } else {
                System.out.println("Produto com ID " + idProduto + " não encontrado na venda " + idVenda);
            }

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

    }


    @Override
    public void finalizarVenda() {



    }


    @Override
    public void listarVendas() {
        if (vendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada ainda.");
            return;
        }

        for (Map.Entry<Integer, Venda> entry : vendas.entrySet()) {
            Venda venda = entry.getValue();
            System.out.println("--------------------------------------------------");
            System.out.println("REGISTRO DA VENDA");
            System.out.println("Venda ID: " + venda.getId());
            System.out.println("Data: " + venda.getDataHora());
            System.out.println("Cliente: " + (venda.getCliente() != null ? venda.getCliente().getNome() : "Não informado"));
            System.out.println("Itens:");

            double total = 0;

            if (venda.getItens() == null || venda.getItens().isEmpty()) {
                System.out.println("  (Nenhum item adicionado)");
            } else {
                for (ItensVenda item : venda.getItens()) {
                    double subtotal = item.getPreco() * item.getQuantidade();

                    System.out.println("  - " + item.getNome() +
                            " | Qtd: " + item.getQuantidade() +
                            " | Preço: R$" + item.getPreco() +
                            " | Subtotal: R$" + subtotal);
                }
            }

            System.out.println("Total da venda: R$" + venda.getValorTotal());
        }

        System.out.println("--------------------------------------------------");
    }

    @Override
    public void aplicarDesconto(int idVenda) {

        Venda venda = vendas.get(idVenda);

        if (venda == null ){
            System.out.println("venda nao encontrada");
        } else if(venda.getCliente() != null){
            venda.setDesconto(venda.getCliente().getCategoria().getDesconto());
            double procentDesconto = venda.getCliente().getCategoria().getDesconto();
            double valorTotal = venda.getValorTotal();
            double valorDescontado = valorTotal * procentDesconto;
            double valorFinal = valorTotal - valorDescontado;
            venda.setValorTotal(valorFinal);
        } else {
            System.out.println("Nenhum cliente encontrado");
        }



    }





}



