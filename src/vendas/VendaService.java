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

        // eu tenho que adicionar o produto aqui e verificar se a quantidade necessária é a mesma do estoque.



    }

    @Override
    public void removerItemDaVenda() {

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




}



