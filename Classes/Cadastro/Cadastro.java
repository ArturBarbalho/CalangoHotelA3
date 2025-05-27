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
                response.append("ID: ").append(id)
                        .append(", Nome: ").append(nome)
                        .append(", Email: ").append(email)
                        .append(", Telefone: ").append(telefone).append("\n");
            }
            
            stmt.close();
            conexao.close();
            
            return response.toString();

        } catch (SQLException e) {
            return"❌ Erro ao listar hóspedes: " + e.getMessage();
        }
    }
    public boolean cadastrarHospede(String nome, String email, String telefone) {
        // Implementar lógica para cadastrar um hóspede
        return true; // Retornar true se o cadastro for bem-sucedido
    }
    public boolean removerHospede(int id) {
        // Implementar lógica para remover um hóspede
        return true; // Retornar true se a remoção for bem-sucedida
    }
}
