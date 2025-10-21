package produtos;

import java.time.LocalDateTime;

public class Registro {

    private int idProduto;
    private String tipo; // "ENTRADA" ou "SAIDA"
    private int quantidade;
    private LocalDateTime dataHora;
    private String descricao;

    public Registro(int idProduto, String tipo, int quantidade, String descricao) {
        this.idProduto = idProduto;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.dataHora = LocalDateTime.now();
    }

    public int getIdProduto() {
        return idProduto;
    }

    public String getTipo() {
        return tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return "[" + dataHora + "] " + tipo + " - ID Produto: " + idProduto + " (" + "quantidade atual: " + quantidade + ") " + descricao;
    }
}
