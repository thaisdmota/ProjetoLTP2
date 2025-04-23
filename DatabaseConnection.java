package projeto1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/agencia_viagens";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "ceub123456";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }

    public static void desconectar(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Conexão encerrada com sucesso.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            Connection connection = DatabaseConnection.getConnection();
            System.out.println("Conectado com sucesso ao banco de dados!");
            desconectar(connection);
        } catch (SQLException e) {
            System.err.println("Erro ao conectar com banco de dados: " + e.getMessage());
        }
    }
}

