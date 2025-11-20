package produtos;

import banco.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutosDAO {

    public void inserir(Produto p) {

        String sql = "INSERT INTO produto (nome, cod_barras, preco, custo_medio, qtd_estoque) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getCodigoBarras());
            stmt.setDouble(3, p.getPreco());
            stmt.setDouble(4, p.getCustoMedio());
            stmt.setInt(5, p.getEstoque());

            stmt.executeUpdate();

            // PEGAR O ID GERADO
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                p.setId(rs.getInt(1));
            }

        } catch (Exception e) {
            System.out.println("Erro ao inserir produto: " + e.getMessage());
        }
    }

    public List<Produto> listar() {

        List<Produto> produtos = new ArrayList<>();

        String sql = "SELECT * FROM produto";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produto p = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cod_barras"),
                        rs.getDouble("preco"),
                        rs.getDouble("custo_medio"),
                        rs.getInt("qtd_estoque")
                );

                produtos.add(p);
            }

        } catch (Exception e) {
            System.out.println("Erro na listagem: " + e.getMessage());
        }

        return produtos;
    }

    public Produto buscarPorId(int id) {

        String sql = "SELECT * FROM produto WHERE id = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("cod_barras"),
                        rs.getDouble("preco"),
                        rs.getDouble("custo_medio"),
                        rs.getInt("qtd_estoque")
                );
            }

        } catch (Exception e) {
            System.out.println("Erro ao buscar: " + e.getMessage());
        }

        return null;
    }

    public void atualizarPreco(int id, double preco) {

        String sql = "UPDATE produto SET preco = ? WHERE id = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setDouble(1, preco);
            stmt.setInt(2, id);
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Erro ao atualizar preço: " + e.getMessage());
        }
    }

    public void excluir(int id) {

        String sql = "DELETE FROM produto WHERE id = ?";

        try (Connection con = ConnectionFactory.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Erro ao excluir produto: " + e.getMessage());
        }
    }
}
