package org.example;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<String> getAllUsersLogins() {
        return this.userRepository.getAllUsers()
                .stream().map(User::getLogin).collect(Collectors.toList());
    }

    public void addNewUser(String login, String password) throws UserNonUniqueException {
        User user = new User(login,password);

        if(login == null || login.isBlank() || password == null || password.isBlank()) {
            throw new IllegalArgumentException("Поля логин и/или пароль не могут быть пустыми!");
        }

        boolean userExist = this.getAllUsersLogins()
                .stream()
                .anyMatch(u -> u.equals(login));

        if(userExist) {
            throw new UserNonUniqueException("Такой логин уже сущестует!");
        }

        this.userRepository.addUser(user);
    }

    public boolean loginUser(String login, String password) {
        if (getAllUsersLogins().contains(login) & getAllUsersLogins().contains(password)) {
            return true;
        } else {
            return false;
        }
    }
}
