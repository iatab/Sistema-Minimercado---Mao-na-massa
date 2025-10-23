package vendas;

import clientes.Cliente;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Venda {

    private int id ;
    private LocalDateTime dataHora;
    private Cliente cliente;
    private ArrayList<ItensVenda> itens;
    private double desconto;
    private double valorTotal;


    public Venda(int id) {
        this.id = id;
        this.dataHora = LocalDateTime.now();
        this.itens = new ArrayList<>();
        this.valorTotal = valorTotal;
    }

    public Venda(int id, Cliente cliente, double desconto, double valorTotal) {
        this.id = id;
        this.dataHora = LocalDateTime.now();
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.desconto = desconto;
        this.valorTotal = valorTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<ItensVenda> getItens() {
        return itens;
    }

    public void setItens(ArrayList<ItensVenda> itens) {
        this.itens = itens;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "id=" + id +
                ", dataHora=" + dataHora +
                ", cliente=" + cliente +
                ", itens=" + itens +
                ", desconto=" + desconto +
                ", valorTotal=" + valorTotal +
                '}';
    }
}
