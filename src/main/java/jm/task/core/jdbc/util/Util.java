package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util implements AutoCloseable {

    private Connection connection;
    private SessionFactory factory;
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";


    public Util() throws SQLException, MalformedURLException {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        factory = new Configuration()
                .setProperty("hibernate.connection.url", URL)
                .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
                .setProperty("hibernate.connection.username", USERNAME)
                .setProperty("hibernate.connection.password", PASSWORD)
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                .setProperty("hibernate.current_session_context_class", "thread")
                .addAnnotatedClass(User.class).buildSessionFactory();
    }

    public Connection getConnection() {
        return connection;
    }

    public Session getSession() {
        return factory.getCurrentSession();
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
