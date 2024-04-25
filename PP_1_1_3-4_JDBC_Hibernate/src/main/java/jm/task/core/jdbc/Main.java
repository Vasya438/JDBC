package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    private static final UserService userService = new UserServiceImpl();
    public static void main(String[] args) {
        User kolya = new User("Kolya", "Ivanov", (byte) 55);
        User valya = new User("Valya", "Ivanova", (byte) 15);
        User ira = new User("Ira", "Ivanova", (byte) 22);
        User sem = new User("Sem", "Ivanov", (byte) 44);

        userService.createUsersTable();
        userService.saveUser(kolya.getName(), kolya.getLastName(), kolya.getAge());
        System.out.println("User с именем — " + kolya.getName() +" добавлен в базу данных");
        userService.saveUser(valya.getName(), valya.getName(), valya.getAge());
        System.out.println("User с именем — " + valya.getName() +" добавлен в базу данных");
        userService.saveUser(ira.getName(), ira.getName(), ira.getAge());
        System.out.println("User с именем — " + ira.getName() +" добавлен в базу данных");
        userService.saveUser(sem.getName(), sem.getName(), sem.getAge());
        System.out.println("User с именем — " + sem.getName() +" добавлен в базу данных");
        List<User> allUsers = userService.getAllUsers();
        System.out.println(allUsers);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
