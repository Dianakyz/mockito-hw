import org.example.User;
import org.example.UserNonUniqueException;
import org.example.UserRepository;
import org.example.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.NoInteractions;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void whenCorrectUserIsAddedThenAddUserIsCalledFromRepo() throws UserNonUniqueException {
        when(userRepository.getAllUsers()).thenReturn(List.of());
        when(userRepository.addUser(any(User.class))).thenReturn(null);
        userService.addNewUser("Max", "test345");
        verify(userRepository).addUser(any());
    }

    @Test
    void whenInvalidUserIsPassedThenServiceThrowsException() {
        assertThatThrownBy(() -> userService.addNewUser("", ""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Поля логин и/или пароль не могут быть пустыми!");
        verify(userRepository, new NoInteractions()).getAllUsers();
        verify(userRepository, new NoInteractions()).addUser(any());
    }

    @Test
    void whenExistingUserIsPassedThenServiceThrowsException() {
        when(userRepository.getAllUsers()).thenReturn(List.of(new User("Pavel", "testTEST")));
        assertThatThrownBy(() -> userService.addNewUser("Pavel", "testTEST"))
                .isInstanceOf(UserNonUniqueException.class)
                .hasMessage("Такой логин уже сущестует!");
    }

}
