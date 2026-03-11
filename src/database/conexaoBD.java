package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexaoBD {

    private static final String URL = "jdbc:mysql://localhost:3306/gestao_projetos";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";


    public static Connection conectar() throws SQLException {
        try{

            Class.forName("com.mysql.jdbc.Driver");

            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException("Erro na conexão: " + e.getMessage());
        }

    }


}

