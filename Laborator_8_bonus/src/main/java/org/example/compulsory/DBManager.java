package org.example.compulsory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBManager {
    private static final String URL ="jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USER = "music";
    private static final String PASSWORD = "MUSIC";
    private Connection connection;
    private static DBManager instance;

    public Connection getConnection() {
        return connection;
    }

    private DBManager() {
        try {
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected!");
//            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("Error at connection!");
            System.err.println(e);
        }
    }

    public static DBManager getInstance() throws SQLException{
        if (instance == null) {
            instance = new DBManager();
        }else if(instance.getConnection().isClosed()){
            instance = new DBManager();
        }
        return instance;
    }
    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
}
