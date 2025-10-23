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
    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes);
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

