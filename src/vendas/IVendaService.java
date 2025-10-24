package vendas;

import produtos.Produto;


public interface IVendaService {

    void iniciarVenda(Venda venda);





    void adicionarItemDaVenda(int idProduto, int idVenda, int quantidade);



    void removerItemDaVenda(int idProduto, int idVenda);

    void finalizarVenda();
    void listarVendas();



    void aplicarDesconto(int idVenda);
}
