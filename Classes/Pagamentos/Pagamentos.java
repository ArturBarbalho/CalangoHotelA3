package Classes.Pagamentos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Classes.Database.ConexaoSQL;

public class Pagamentos {

    public void salvarPagamento(int valor, int data, String metodo) {
        String sql = "INSERT INTO Pagamentos (valor, data, metodo) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoSQL.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, valor);
            stmt.setInt(2, data);
            stmt.setString(3, metodo);
            

            stmt.executeUpdate();
            System.out.println("✅ Dados de pagamento inseridos no banco com sucesso!");

        } catch (SQLException e) {
            System.out.println("❌ Erro ao inserir dados no banco: " + e.getMessage());
        }
    }


    public List<Map<String, Object>> listarPagamentos() {
    String sql = "SELECT * FROM Pagamentos";
    List<Map<String, Object>> listaDeDados = new ArrayList<>();

    try (Connection conn = ConexaoSQL.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        ResultSetMetaData metaData = rs.getMetaData();
        int colunas = metaData.getColumnCount();

        while (rs.next()) {
            Map<String, Object> linha = new HashMap<>();
            for (int i = 1; i <= colunas; i++) {
                String nomeColuna = metaData.getColumnName(i);
                Object valor = rs.getObject(i);
                linha.put(nomeColuna, valor);
            }
            listaDeDados.add(linha);
        }

    } catch (SQLException e) {
        System.out.println("❌ Erro ao consultar dados no banco: " + e.getMessage());
        return null;
    }

    return listaDeDados;
}

}
