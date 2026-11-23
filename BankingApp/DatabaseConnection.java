package BankingApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException; 

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/DatabaseConnection";
    private static final String USER = "root"; //root user at DB
    private static final String PASSWORD = "Update using your gmail account in github"; // password at DB

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
} 
