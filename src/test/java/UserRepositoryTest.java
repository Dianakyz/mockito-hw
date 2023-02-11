import org.example.User;
import org.example.UserNonUniqueException;
import org.example.UserRepository;
import org.example.UserService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryTest {

    UserRepository userRepository = new UserRepository();
    UserService userService = new UserService(userRepository);

    @Test
    static void getEmptyList() {
        Collection<User> getAllUsersExpected = new ArrayList<>();
        Collection<User> getAllUsersActual = new ArrayList<>();
        assertEquals(getAllUsersExpected, getAllUsersActual);
    }

    @Test
    void getFullList() throws UserNonUniqueException {
        User user1 = new User("Ann", "test1");
        User user2 = new User("Masha", "test222");
        User user3 = new User("Tom", "12345");

        UserRepository userRepository = new UserRepository();

        userRepository.addUser(user1);
        userRepository.addUser(user2);
        userRepository.addUser(user3);

        Collection<User> expected = userRepository.getAllUsers();

        Collection<User> actual = new ArrayList<>();
        actual.add(user1);
        actual.add(user2);
        actual.add(user3);

        assertEquals(expected, actual);

    }

    @Test
    void searchUserByLogin() throws UserNonUniqueException {
        userService.addNewUser("Ann", "test1");
        userRepository.getUserByLogin("Ann");
    }

    @Test
    void searchUserByLoginIfLoginIsEmpty() throws UserNonUniqueException {
        userService.addNewUser("Ann", "test1");
        userRepository.getUserByLogin("Max");
    }

    @Test
    void searchUserByLoginAndPassword() throws UserNonUniqueException {
        userService.addNewUser("Ann", "test1");
        userRepository.getUserByLoginAndPassword("Ann", "test1");
    }

    @Test
    void searchUserByLoginAndPasswordIfUserIsNull() throws UserNonUniqueException {
        userService.addNewUser("Ann", "test1");
        userRepository.getUserByLoginAndPassword("Max", "test1");
    }

    @Test
    void searchUserByLoginAndPasswordIfLoginIsExistButPasswordNull() throws UserNonUniqueException {
        userService.addNewUser("Ann", "test1");
        userRepository.getUserByLoginAndPassword("Ann", "test123");
    }
}
