import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
    private static Connection instance;
    private Connection connection;

    private Connection() throws SQLException {
        String url = "jdbc:mysql://localhost:5741/bd";
        String username = "bd";
        String password = "1234";
        connection = DriverManager.getConnection(url, username, password);
    }

    public static synchronized Connection getInstance() throws SQLException {
        if (instance == null) {
            instance = new Connection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}