package org.example;

public class Main {
    public static void main(String[] args) throws UserNonUniqueException {

        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);

        userService.addNewUser("Ann", "test1");
        userService.addNewUser("Masha", "test222");
        userService.addNewUser("Tom", "12345");

        //userService.addNewUser("Ann", "g kfjnd"); // для проверки работы исключения
        //userService.addNewUser("Ann", "jdszfxghjk"); // для проверки работы исключения


        System.out.println(userRepository.getAllUsers());
        System.out.println(userService.getAllUsersLogins());
    }
}