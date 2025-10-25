package clientes;

public class ClientePessoaFisica extends Cliente {

    private String cpf;

    public ClientePessoaFisica(int id, String nome, String cpf, String telefone, Categoria categoria) {
        super(id, nome, telefone, categoria);

        if (!isCpfValido(cpf)) {
            throw new IllegalArgumentException("CPF inválido: " + cpf);
        }

        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (!isCpfValido(cpf)) {
            throw new IllegalArgumentException("CPF inválido: " + cpf);
        }
        this.cpf = cpf;
    }

    // Método privado para validar CPF
    private boolean isCpfValido(String cpf) {
        if (cpf == null) return false;

        String cpfNumbers = cpf.replaceAll("[^0-9]", "");

        if (cpfNumbers.length() != 11) return false;

        if (cpfNumbers.matches("(\\d)\\1{10}")) return false;

        try {
            int sum1 = 0;
            int sum2 = 0;

            for (int i = 0; i < 9; i++) {
                int num = Integer.parseInt(cpfNumbers.substring(i, i + 1));
                sum1 += num * (10 - i);
                sum2 += num * (11 - i);
            }

            int dv1 = sum1 % 11 < 2 ? 0 : 11 - (sum1 % 11);
            sum2 += dv1 * 2;
            int dv2 = sum2 % 11 < 2 ? 0 : 11 - (sum2 % 11);

            return dv1 == Integer.parseInt(cpfNumbers.substring(9, 10)) &&
                    dv2 == Integer.parseInt(cpfNumbers.substring(10, 11));
        } catch (NumberFormatException e) {
            return false;
        }
    }


    @Override
    public String toString() {
        return String.format("%-5s %-20s %-15s %-10s %-15s", getId(), getNome(), getTelefone(), getCategoria(), cpf);
    }
}
