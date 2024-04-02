package org.example.chinesfoodcafe.service;

import org.example.chinesfoodcafe.entity.User;
import org.example.chinesfoodcafe.repository.VerificationRepository;
import org.example.chinesfoodcafe.utils.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class VerificationServiceTest {
    @Mock
    private VerificationRepository verificRepository;

    @Test
    public void verif_account() {
        User user = new User("Mary@Gamil.com", "1212", Role.ROLE_USER);
        verificRepository.save(user);
        User user1 = verificRepository.findByEmail(user.getEmail());
        User save = verificRepository.save(user1);
        assertEquals(user1,save);

    }
}
