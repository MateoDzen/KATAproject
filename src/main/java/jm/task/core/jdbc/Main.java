package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan", "Ivanov", (byte) 22);
        userService.saveUser("Petr", "Petrov", (byte) 21);
        userService.saveUser("Mariya", "Zaborova", (byte) 23);
        userService.saveUser("Marina", "Sidorova", (byte) 19);
        System.out.println(userService.getAllUsers());
        userService.dropUsersTable();

    }
}
