package com.example.definitelynotrobots;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton object to create a connection to the local database.
 */
public class DatabaseConnection {
    /**
     * Driver connection to the database.
     */
    private static Connection instance = null;

    /**
     * Initializes the instance of DatabaseConnection.
     */
    private DatabaseConnection() {
        String url = "jdbc:sqlite:database.db";
        try {
            instance = DriverManager.getConnection(url);
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx);
        }
    }

    /**
     * Creates a connection to the database.
     */
    public static Connection getInstance() {
        if (instance == null) {
            new DatabaseConnection();
        }
        return instance;
    }
}