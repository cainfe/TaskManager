package main.java.com.cainfe.task_manager.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Database {
    private static final String URL = "jdbc:sqlite:task_manager.db";
    
    public static Connection getConnection() {
        Connection connection = null;
        
        try {
		    connection = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
