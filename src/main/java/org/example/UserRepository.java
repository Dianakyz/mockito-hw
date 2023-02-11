package org.example;

import org.example.User;

import java.util.*;

public class UserRepository {

    private final List<User> users = new ArrayList<>();

    public Collection<User> getAllUsers() {
        return Collections.unmodifiableCollection(users);
    }

    public Optional<User> getUserByLogin(String login) {
        return this.users.stream().filter(user -> user.getLogin().equals(login)).findAny();
    }

    public Optional<User> getUserByLoginAndPassword(String login, String password) {
        return this.users.stream().filter(user -> user.getLogin().equals(login)).filter(user -> user.getPassword().equals(password)).findAny();
    }

    public void addUser(User user) {
        this.users.add(user);
    }
}
