package org.example.chinesfoodcafe.service;

import org.example.chinesfoodcafe.entity.User;
import org.example.chinesfoodcafe.repository.UserRepository;
import org.example.chinesfoodcafe.utils.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void register_user(){
        User user = new User("Mary@Gamil.com", "1212", Role.ROLE_USER);
        userRepository.save(user);
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(false);
        User user1 = new User("Mary@Gamil.com", "1212", Role.ROLE_USER);
        userService.register(user1);
        verify(userRepository.save(user1));
    }
    @Test
    public void get_user_by_id(){
        Long id = 1L;
        User user = new User("Mary@Gamil.com", "1212", Role.ROLE_USER);
        when(userRepository.findUserById(id)).thenReturn(user);
        User result = userService.getUserById(id);
        assertEquals(user, result);
    }
    @Test
    public void delete_user_by_id(){
        Long id = 1L;
        String string = userService.deleteUserById(id);
        verify(userRepository, times(1)).deleteById(id);
    }
    @Test
    public void get_all_user(){
        String email = "example@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
        Optional<User> result = userService.findByEmail(email);

        verify(userRepository, times(1)).findByEmail(email);
        assertEquals(Optional.empty(), result);
    }
    @Test
    public void update_user(){
        Long id = 1L;
        User user = new User("Mary@Gamil.com", "1212", Role.ROLE_USER);
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        user.setEmail("mail@mail.com");
        user.setRole(Role.ROLE_ADMIN);
        user.setPassword("222");
        when(userRepository.save(user)).thenReturn(user);
        User result = userService.updateUser(user, id);
        assertEquals(user, result);

    }
}
