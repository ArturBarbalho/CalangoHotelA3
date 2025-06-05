package Classes.Cadastro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Classes.Database.ConexaoSQL;

public class Cadastro {

    public String listarHospedes() {
        Connection conexao = ConexaoSQL.conectar();
        String sql = "SELECT * FROM Hospedes";
        StringBuilder response = new StringBuilder();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String nome = rs.getString("Nome");
                String email = rs.getString("Email");
                String telefone = rs.getString("Telefone");
                int quarto = rs.getInt("Quarto");
                String dataEntrada = rs.getString("DataEntrada");
                String dataSaida = rs.getString("DataSaida");
                int valor = rs.getInt("Valor");
                String formaPagamento = rs.getString("FormaPagamento");

                response.append("ID: ").append(id)
                        .append(", Nome: ").append(nome)
                        .append(", Email: ").append(email)
                        .append(", Telefone: ").append(telefone)
                        .append(", Quarto: ").append(quarto)
                        .append(", Data de Entrada: ").append(dataEntrada)
                        .append(", Data de Saída: ").append(dataSaida)
                        .append(", Valor: ").append(valor)
                        .append(", Forma de Pagamento: ").append(formaPagamento)
                        .append("\n");
            }

            stmt.close();
            conexao.close();

            return response.toString();

        } catch (SQLException e) {
            return "❌ Erro ao listar hóspedes: " + e.getMessage();
        }
    }

    public void cadastrarHospede(String nome, String email, int telefone, int quarto,
                                 String dataEntrada, String dataSaida, int valor, String formaPagamento) {
        String sql = "INSERT INTO Hospedes (Nome, Email, Telefone, Quarto, DataEntrada, DataSaida, Valor, FormaPagamento) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexao = ConexaoSQL.conectar(); 
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setInt(3, telefone);
            stmt.setInt(4, quarto);
            stmt.setString(5, dataEntrada);
            stmt.setString(6, dataSaida);
            stmt.setInt(7, valor);
            stmt.setString(8, formaPagamento);

            stmt.executeUpdate();
            System.out.println("✅ Hóspede cadastrado com sucesso.");

        } catch (SQLException e) {
            System.out.println("❌ Erro ao cadastrar hóspede: " + e.getMessage());
        }
    }

    public void removerHospede(int id) {
        String sql = "DELETE FROM Hospedes WHERE ID = ?";

        try (Connection conexao = ConexaoSQL.conectar(); 
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Hóspede removido com sucesso.");
            } else {
                System.out.println("⚠️ Hóspede com ID " + id + " não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Erro ao remover hóspede: " + e.getMessage());
        }
    }
}
