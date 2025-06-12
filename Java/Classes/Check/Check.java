package Classes.Check;
import org.json.JSONArray;
import org.json.JSONObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Classes.Database.ConexaoSQL;

public class Check {

    // Método para realizar o Check-in
    public boolean realizarCheckIn(int idReserva) {
        Connection conexao = ConexaoSQL.conectar();
        String sql = "UPDATE Reservas SET Status = 'Ocupado' WHERE ID = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idReserva);

            int linhasAfetadas = stmt.executeUpdate();
            stmt.close();
            conexao.close();

            return linhasAfetadas > 0;
        } catch (SQLException e) {
            System.out.println("❌ Erro ao fazer Check-in: " + e.getMessage());
            return false;
        }
    }

    // Método para realizar o Check-out
    public boolean realizarCheckOut(int idReserva) {
        Connection conexao = ConexaoSQL.conectar();
        String sql = "UPDATE Reservas SET Status = 'Finalizada' WHERE ID = ?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idReserva);

            int linhasAfetadas = stmt.executeUpdate();
            stmt.close();
            conexao.close();

            return linhasAfetadas > 0;
        } catch (SQLException e) {
            System.out.println("❌ Erro ao fazer Check-out: " + e.getMessage());
            return false;
        }
    }

    
public String listarReservasAguardandoCheckIn() {
    Connection conexao = ConexaoSQL.conectar();
    String sql = "SELECT * FROM Reservas";
    JSONArray reservasJson = new JSONArray();

    try {
        PreparedStatement stmt = conexao.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            JSONObject reserva = new JSONObject();
            reserva.put("id", rs.getInt("ID"));
            reserva.put("quarto", rs.getInt("Quarto"));
            reserva.put("cliente", rs.getString("Cliente"));
            reserva.put("checkIn", rs.getString("Data_CheckIn"));
            reserva.put("checkOut", rs.getString("Data_CheckOut"));
            reserva.put("status", rs.getString("Status"));

            reservasJson.put(reserva);
        }

        rs.close();
        stmt.close();
        conexao.close();

        return reservasJson.toString(); // retorna JSON em formato de array
    } catch (Exception e) {
        e.printStackTrace();
        return "[]"; // retorna array vazio em caso de erro
    }
}
    
    public void NovaReserva(String Cliente, int Quarto, String dataEntrada, String dataSaida) {
        Connection conexao = ConexaoSQL.conectar();
        String sql = "INSERT INTO Reservas (Cliente, Quarto, Data_CheckIn, Data_CheckOut, Status) VALUES (?, ?, ?, ?, 'Aguardando Check-in')";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, Cliente);
            stmt.setInt(2, Quarto);
            stmt.setString(3, dataEntrada);
            stmt.setString(4, dataSaida);

            int linhasAfetadas = stmt.executeUpdate();
            stmt.close();
            conexao.close();

            if (linhasAfetadas > 0) {
                System.out.println("✅ Reserva criada com sucesso!");
            } else {
                System.out.println("❌ Falha ao criar reserva.");
            }
        } catch (SQLException e) {
            System.out.println("❌ Erro ao criar reserva: " + e.getMessage());
        }        
        
    }
}
