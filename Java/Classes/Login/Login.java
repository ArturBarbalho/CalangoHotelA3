package Classes.Login;

import Classes.Database.ConexaoSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

    // Método para verificar se o usuário e senha existem no banco
    public static boolean verificarLogin(String usuario, String senha) {
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean autenticado = false;

        try {
            
            conexao = ConexaoSQL.conectar();

            
            String sql = "SELECT * FROM Funcionarios WHERE usuario = ? AND senha = ?";

            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setString(2, senha);

            rs = stmt.executeQuery();

            // Se encontrar algum registro, significa que o login está correto
            if (rs.next()) {
                autenticado = true;
            }

        } catch (SQLException e) {
            System.out.println("❌ Erro ao verificar login: " + e.getMessage());
        } finally {
            // Fechar conexão, statement e resultset
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                System.out.println("❌ Erro ao fechar conexão: " + e.getMessage());
            }
        }

        return autenticado;
    }
}
