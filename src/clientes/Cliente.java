package clientes;

public abstract class Cliente {

    private int id;
    private String nome;
    private String telefone;
    private Categoria categoria;

    public Cliente(int id, String nome, String telefone, Categoria categoria) {
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
        this.telefone = telefone;
        this.categoria = categoria;
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
        if(nome.isEmpty()) {
            throw new NullPointerException("nome nao pode estar vazio");
        }    else {
            this.nome = nome;
        }

    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {



            if (telefone == null || telefone.trim().isEmpty()) {
                throw new IllegalArgumentException("Telefone inválido: não pode ser vazio.");
            }

            // Verifica se contém letras (A-Z ou a-z)
            if (telefone.matches(".*[a-zA-Z].*")) {
                throw new IllegalArgumentException("Telefone inválido: não pode conter letras.");
            }

            // Verifica se contém apenas caracteres permitidos
            if (!telefone.matches("[0-9()+\\-\\s]+")) {
                throw new IllegalArgumentException("Telefone inválido: contém caracteres não permitidos.");
            }

            this.telefone = telefone;
        }




    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }


    @Override
    public String toString() {
        return String.format("%-5s %-20s %-15s %-10s", id, nome, telefone, categoria);
    }
}
