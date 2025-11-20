package produtos;

public class Produto {

    // os produtos estao no estoque


    private int id;
    private String nome;
    private String codigoBarras;
    private double preco;
    private double custoMedio;
    private int estoque;


    public Produto(int id, String nome, String codigoBarras, double preco, double custoMedio, int estoque) {

        if (id < 0 ){
            throw new NullPointerException("nao pode criar um produto com id negativo");
        } else
        {
            this.id = id;
        }
        if( nome.isEmpty()) {
           throw new NullPointerException("nome nao pode estar vazio");
        } else {
            this.nome = nome;
        }

        if( codigoBarras.isEmpty()) {
            throw new NullPointerException("codigo de barras nao pode estar vazio");
        } else {
            this.codigoBarras = codigoBarras;
        }

        if(preco < 0) {
            throw new NullPointerException("preco negativo");
        } else {
            this.preco = preco;
        }


        this.custoMedio = custoMedio;

        if(estoque < 0 ) {

            throw new NullPointerException("nao é possivel cadastrar um produto com estoque negativo");
        }
        this.estoque = estoque;
    }


    public Produto(String nome, String codigoBarras, double preco, double custoMedio, int estoque) {

        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("nome nao pode estar vazio");
        }
        if (codigoBarras == null || codigoBarras.isEmpty()) {
            throw new IllegalArgumentException("codigo de barras nao pode estar vazio");
        }
        if (preco < 0) {
            throw new IllegalArgumentException("preco negativo");
        }
        if (estoque < 0) {
            throw new IllegalArgumentException("estoque negativo");
        }

        // id fica SEM valor (0) até o BD retornar
        this.nome = nome;
        this.codigoBarras = codigoBarras;
        this.preco = preco;
        this.custoMedio = custoMedio;
        this.estoque = estoque;
    }





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco < 0 ) {
            System.out.println("Operaçao ilegal, valor do produto negativo");
        } else {
            this.preco = preco;
            System.out.println("valor alterado com sucesso");
        }

    }

    public double getCustoMedio() {
        return custoMedio;
    }

    public void setCustoMedio(double custoMedio) {
        this.custoMedio = custoMedio;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        if (estoque < 0 ) {
            System.out.println("Operaçao ilegal, valor do estoque negativo");
        } else {
            this.estoque = estoque;
        }


    }
}
