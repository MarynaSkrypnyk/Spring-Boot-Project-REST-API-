package org.example.chinesfoodcafe.repository;

import org.example.chinesfoodcafe.entity.User;
import org.example.chinesfoodcafe.utils.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void save_user(){
        User user = new User("Mary@Gamil.com","1212", Role.ROLE_USER);
        userRepository.save(user);
        Assertions.assertNotNull(user.getId());
    }

    @Test
    public void user_exist_by_email(){
        User user = new User("Mary@Gamil.com","1212", Role.ROLE_USER);
        userRepository.save(user);
        boolean present = userRepository.existsByEmail(user.getEmail());
        assertTrue(present);
    }
    @Test
    public void optional_find_by_email(){
        User user = new User("Mary@Gamil.com","1212", Role.ROLE_USER);
        userRepository.save(user);
        userRepository.findByEmail(user.getEmail());
        String email = "Mary@Gamil.com";

        Optional<User> optionalUser = userRepository.findByEmail(email);

        Assertions.assertTrue(optionalUser.isPresent());
        User retrievedUser = optionalUser.get();
        Assertions.assertEquals(user.getEmail(), retrievedUser.getEmail());

    }
    @Test
    public void find_all_user(){
        User user = new User("Mary@Gamil.com","1212", Role.ROLE_USER);
        User user2 = new User("Mary2@Gamil.com","1212", Role.ROLE_USER);
        userRepository.save(user);
        userRepository.save(user2);

        Page<User> usersPage = userRepository.getAllUsers(PageRequest.of(0, 10));

        assertEquals(2, usersPage.getTotalElements());
    }

    @Test
    public void find_user_by_id(){
        User user = new User("Mary@Gamil.com","1212", Role.ROLE_USER);
        userRepository.save(user);
        User userById = userRepository.findUserById(user.getId());
        assertEquals(user,userById);
    }
}
