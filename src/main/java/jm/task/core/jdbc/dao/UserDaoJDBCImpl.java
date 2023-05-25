package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Util util = new Util(); Statement statement = util.getConnection().createStatement()) {
            statement.execute("CREATE TABLE mydbtest.users (" +
                    "id BIGINT NOT NULL  AUTO_INCREMENT," +
                    "name VARCHAR(45) NOT NULL," +
                    "lastname VARCHAR(45) NOT NULL," +
                    "age INT NOT NULL," +
                    "PRIMARY KEY (id))");
        } catch (Exception e) {
            System.out.println("Таблица users уже существует!");
        }
    }

    @Override
    public void dropUsersTable() {
        try (Util util = new Util(); Statement statement = util.getConnection().createStatement()) {
            statement.execute("DROP TABLE  mydbtest.users");
        } catch (Exception e) {
            System.out.println("Таблицы users не существует!");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Util util = new Util(); PreparedStatement statement = util.getConnection()
                .prepareStatement("INSERT INTO mydbtest.users (name, lastname, age) VALUES (?,?,?)")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Util util = new Util(); PreparedStatement statement = util.getConnection()
                .prepareStatement("DELETE FROM mydbtest.users WHERE id=?")) {
            statement.setInt(1, (int) id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Util util = new Util(); PreparedStatement statement = util.getConnection()
                .prepareStatement("SELECT  * FROM mydbtest.users")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(new User(resultSet.getString(2), resultSet.getString(3)
                        , resultSet.getByte(4)).setId(resultSet.getLong(1)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try (Util util = new Util(); Statement statement = util.getConnection()
                .createStatement()) {
            statement.executeUpdate("DELETE FROM mydbtest.users");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
