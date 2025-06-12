package Classes.Check;

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

    // Método para consultar reservas aguardando check-in
    public String listarReservasAguardandoCheckIn() {
        Connection conexao = ConexaoSQL.conectar();
        String sql = "SELECT * FROM Reservas WHERE Status = 'Aguardando Check-in'";
        StringBuilder response = new StringBuilder();

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            response.append("📜 Reservas aguardando Check-in:\n");
            while (rs.next()) {
                int id = rs.getInt("ID");
                int Quarto = rs.getInt("Quarto");
                int idCliente = rs.getInt("ID_Cliente");
                String checkIn = rs.getString("Data_CheckIn");
                String checkOut = rs.getString("Data_CheckOut");

                response.append("Reserva ID: ").append(id)
                        .append(", Quarto: ").append(Quarto)
                        .append(", Cliente: ").append(idCliente)
                        .append(", Check-In: ").append(checkIn)
                        .append(", Check-Out: ").append(checkOut)
                        .append("\n");
            }

            rs.close();
            stmt.close();
            conexao.close();

            return response.toString().trim();
        } catch (SQLException e) {
            String errorMessage = "❌ Erro ao listar reservas: " + e.getMessage();
            return errorMessage;
        }
    }
    
    public void NovaReserva(String ID_Cliente, int Quarto, String dataEntrada, String dataSaida) {
        Connection conexao = ConexaoSQL.conectar();
        String sql = "INSERT INTO Reservas (ID_Cliente, Quarto, Data_CheckIn, Data_CheckOut, Status) VALUES (?, ?, ?, ?, 'Aguardando Check-in')";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, ID_Cliente);
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
