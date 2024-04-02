package org.example.chinesfoodcafe.repository;

import org.example.chinesfoodcafe.entity.User;
import org.example.chinesfoodcafe.utils.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class VerificationRepositoryTest {

    @Autowired
    private VerificationRepository verificationRepository;

    @Test
    public void find_user_by_id(){
        User user = new User("Mary@Gamil.com","1212", Role.ROLE_USER);
        verificationRepository.save(user);
        User userByEmail = verificationRepository.findByEmail(user.getEmail());
        assertEquals(user,userByEmail);
    }
}
