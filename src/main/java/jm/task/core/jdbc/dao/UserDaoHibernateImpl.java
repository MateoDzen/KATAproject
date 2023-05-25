package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.exception.SQLGrammarException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = (new Util()).getSession()) {
            session.beginTransaction();
            session.createNativeQuery("CREATE TABLE mydbtest.users (" +
                    "id BIGINT NOT NULL  AUTO_INCREMENT," +
                    "name VARCHAR(45) NOT NULL," +
                    "lastname VARCHAR(45) NOT NULL," +
                    "age INT NOT NULL," +
                    "PRIMARY KEY (id))", User.class).executeUpdate();
            session.getTransaction().commit();
        } catch (MalformedURLException | SQLException | SQLGrammarException e) {
            System.out.println("Таблица users уже существует!");
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = (new Util()).getSession()) {
            session.beginTransaction();
            session.createNativeQuery("DROP TABLE  mydbtest.users", User.class).executeUpdate();
            session.getTransaction().commit();
        } catch (MalformedURLException | SQLException | SQLGrammarException e) {
            System.out.println("Таблицы users не существует!");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = (new Util()).getSession()) {
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.persist(user);
            session.getTransaction().commit();
        } catch (MalformedURLException | SQLException | SQLGrammarException e) {
           e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = (new Util()).getSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.remove(user);
            session.getTransaction().commit();
        } catch (MalformedURLException | SQLException | SQLGrammarException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Session session = (new Util()).getSession()) {
            session.beginTransaction();
            list = session.createSelectionQuery("from User", User.class).getResultList();
            session.getTransaction().commit();
        } catch (MalformedURLException | SQLException | SQLGrammarException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = (new Util()).getSession()) {
            session.beginTransaction();
            session.createQuery("delete from User", null).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
