package clientes;

public class ClientePessoaJuridica extends Cliente {

    private String cnpj;

    public ClientePessoaJuridica(int id, String nome, String cnpj, String telefone, Categoria categoria) {
        super(id, nome, telefone, categoria);

        if (!isCnpjValido(cnpj)) {
            throw new IllegalArgumentException("CNPJ inválido: " + cnpj);
        }

        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        if (!isCnpjValido(cnpj)) {
            throw new IllegalArgumentException("CNPJ inválido: " + cnpj);
        }
        this.cnpj = cnpj;
    }

    // Método privado para validar CNPJ
    private boolean isCnpjValido(String cnpj) {
        if (cnpj == null) return false;

        String cnpjNumbers = cnpj.replaceAll("[^0-9]", "");

        if (cnpjNumbers.length() != 14) return false;

        // Verifica se todos os dígitos são iguais (inválido)
        if (cnpjNumbers.matches("(\\d)\\1{13}")) return false;

        try {
            int[] pesos1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

            int sum1 = 0;
            for (int i = 0; i < 12; i++) {
                sum1 += Integer.parseInt(cnpjNumbers.substring(i, i + 1)) * pesos1[i];
            }

            int dv1 = sum1 % 11 < 2 ? 0 : 11 - (sum1 % 11);

            int sum2 = 0;
            for (int i = 0; i < 12; i++) {
                sum2 += Integer.parseInt(cnpjNumbers.substring(i, i + 1)) * pesos2[i];
            }
            sum2 += dv1 * pesos2[12];

            int dv2 = sum2 % 11 < 2 ? 0 : 11 - (sum2 % 11);

            return dv1 == Integer.parseInt(cnpjNumbers.substring(12, 13)) &&
                    dv2 == Integer.parseInt(cnpjNumbers.substring(13, 14));
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("%-5s %-20s %-15s %-10s %-20s", getId(), getNome(), getTelefone(), getCategoria(), cnpj);
    }
}
