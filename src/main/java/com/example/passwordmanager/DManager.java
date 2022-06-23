package com.example.passwordmanager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DManager {
    private static final String url = "jdbc:postgresql://localhost/Bio";
    private static final String user = "postgres";
    private static final String password = "projektowe-trio";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}
