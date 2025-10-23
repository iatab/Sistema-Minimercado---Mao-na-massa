package produtos;

import java.util.ArrayList;

public class RegistroService implements  IRegistroService{

    private final ArrayList<Registro> movimentos = new ArrayList<>();

    @Override
    public void registrarEntrada(int idProduto, int quantidade, String descricao) {
        movimentos.add(new Registro(idProduto, "ENTRADA", quantidade, descricao));

    }
    @Override
    public void registrarSaida(int idProduto, int quantidade, String descricao) {
        movimentos.add(new Registro(idProduto, "SAIDA", quantidade, descricao));
    }
    @Override
    public void registrarBaixa(int idProduto, int quantidade, String descricao){
        movimentos.add(new Registro(idProduto, "BAIXA", quantidade, descricao));
    }

    public void listarMovimentos() {
        System.out.println("LISTA DE TODOS OS REGISTROS DO ESTOQUE ");
        for (Registro m : movimentos) {
            System.out.println(m);
        }
    }

    public void listarUltimoMovimento(){
        System.out.println(this.movimentos.getLast());
    }

    public ArrayList<Registro> getMovimentos() {
        return movimentos;
    }


}
