package Classes.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoSQL {

    // Dados da conexão
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=NomeDoSeuBanco";
    private static final String USUARIO = "sa";  // Usuário padrão, se não foi alterado
    private static final String SENHA = "suaSenhaAqui"; // Coloque sua senha correta

    // Método de conexão
    public static Connection conectar() {
        Connection conexao = null;
        try {
            // Registrar o driver JDBC
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Estabelecer a conexão
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("✅ Conexão realizada com sucesso!");

        } catch (ClassNotFoundException e) {
            System.out.println("❌ Driver não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("❌ Erro na conexão: " + e.getMessage());
        }
        return conexao;
    }

    // Método de teste
    public static void main(String[] args) {
        Connection conn = conectar();
        if (conn != null) {
            try {
                conn.close();
                System.out.println("🔌 Conexão encerrada.");
            } catch (SQLException e) {
                System.out.println("❌ Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
}

