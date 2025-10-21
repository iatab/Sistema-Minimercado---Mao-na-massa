package produtos;

public interface IRegistroService {


    void registrarEntrada(int idProduto, int quantidade, String descricao);

    void registrarSaida(int idProduto, int quantidade, String descricao);

    void registrarBaixa(int idProduto, int quantidade, String descricao);
}
