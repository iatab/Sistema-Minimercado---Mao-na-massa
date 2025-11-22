package banco;


import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    private final static String URL = "jdbc:mysql://localhost:3306/prova?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

    private final static String USER = "root";
    private final static String PASS = "senai";

    public static Connection getConnection() {

        try {
            // garante carregamento do driver sem Maven
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(URL, USER, PASS);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao conectar: " + e.getMessage());
        }
    }
}

