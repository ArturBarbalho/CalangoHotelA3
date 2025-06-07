package Classes.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoSQL {

    // Dados da conexão — com autenticação SQL Server (usuário e senha)
    private static final String URL = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=master;encrypt=true;trustServerCertificate=true";
    private static final String USUARIO = "calango";
    private static final String SENHA = "calango123k";

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

    public static void main(String[] args) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);

            if (conexao != null) {
                System.out.println("✅ Conexão com o banco de dados realizada com sucesso!");
                conexao.close();
            }

        } catch (ClassNotFoundException e) {
            System.err.println("❌ Driver JDBC não encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("❌ Erro ao conectar ao banco: " + e.getMessage());
        }
    }
}