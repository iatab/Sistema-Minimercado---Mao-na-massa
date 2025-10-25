package clientes;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class ClienteService {


    private List<Cliente> clientes;


    public ClienteService() {
        this.clientes = new ArrayList<>();
    }


    // CREATE
    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
        System.out.println("Cliente adicionado com sucesso: " + cliente.getNome());
    }


    // - listar todos
    public void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        // Cabeçalho da tabela
        System.out.printf("%-5s %-20s %-15s %-10s %-20s%n", "ID", "Nome", "Telefone", "Categoria", "CPF/CNPJ");
        System.out.println("--------------------------------------------------------------------------");

        // Percorre todos os clientes
        for (Cliente c : clientes) {
            if (c instanceof ClientePessoaFisica) {
                ClientePessoaFisica pf = (ClientePessoaFisica) c;
                System.out.printf("%-5d %-20s %-15s %-10s %-20s%n", pf.getId(), pf.getNome(), pf.getTelefone(), pf.getCategoria(), pf.getCpf());
            } else if (c instanceof ClientePessoaJuridica) {
                ClientePessoaJuridica pj = (ClientePessoaJuridica) c;
                System.out.printf("%-5d %-20s %-15s %-10s %-20s%n", pj.getId(), pj.getNome(), pj.getTelefone(), pj.getCategoria(), pj.getCnpj());
            }
        }
    }


    // - buscar por ID
    public Cliente buscarPorId(int id) {
        Optional<Cliente> cliente = clientes.stream()
                .filter(c -> c.getId() == id)
                .findFirst();


        if (cliente.isPresent()) {
            return cliente.get();
        } else {
            System.out.println("⚠️ Cliente com ID " + id + " não encontrado.");
            return null;
        }
    }


    // UPDATE
    public boolean atualizarCliente(int id, String novoNome, String novoTelefone, Categoria novaCategoria) {
        Cliente cliente = buscarPorId(id);
        if (cliente != null) {
            cliente.setNome(novoNome);
            cliente.setTelefone(novoTelefone);
            cliente.setCategoria(novaCategoria);
            System.out.println("🔁 Cliente atualizado com sucesso!");
            return true;
        }
        return false;
    }


    // DELETE
    public boolean removerCliente(int id) {
        Cliente cliente = buscarPorId(id);
        if (cliente != null) {
            clientes.remove(cliente);
            System.out.println("🗑️ Cliente removido com sucesso!");
            return true;
        }
        System.out.println("⚠️ Cliente com ID " + id + " não encontrado para remoção.");
        return false;
    }
}

