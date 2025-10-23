package vendas;

import produtos.Produto;


public interface IVendaService {

    void iniciarVenda(Venda venda);





    void adicionarItemDaVenda(int idProduto, int idVenda, int quantidade);

    void removerItemDaVenda();
    void finalizarVenda();
    void listarVendas();



}
