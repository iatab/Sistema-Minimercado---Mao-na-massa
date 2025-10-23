package clientes;

public enum Categoria {
    BRONZE(0.0),
    PRATA(0.05),
    OURO(0.10),
    PLATINA(0.15);

    private final double desconto;

    Categoria(double desconto) {
        this.desconto = desconto;
    }

    public double getDesconto() {
        return desconto;
    }
}
