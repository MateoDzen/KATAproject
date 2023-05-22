package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util implements AutoCloseable {

    private Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";


    public Util() throws SQLException {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
