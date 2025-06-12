package Classes.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoSQL {

    // Dados da conexão — com autenticação SQL Server (usuário e senha)
    private static final String URL = "jdbc:postgresql://localhost:5432/hotel";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "1234";

    public static Connection conectar() {
        Connection conexao = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("✅ Conexão realizada com sucesso (Autenticação SQL Server)!");

        } catch (ClassNotFoundException e) {
            System.out.println("❌ Driver não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("❌ Erro na conexão: " + e.getMessage());
        }
        return conexao;
    }

   
}