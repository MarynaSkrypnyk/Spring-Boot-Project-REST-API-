package org.example.chinesfoodcafe.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.chinesfoodcafe.entity.User;
import org.example.chinesfoodcafe.repository.VerificationRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class VerificationService {

    private final VerificationRepository verificRepository;

    public String verifyAccount(String email,String code) {

        User user = verificRepository.findByEmail(email);

        user.setActive(true);
        log.info("verifyAccount : {}",code);
        log.info("verifyAccount : {}",email);

        verificRepository.save(user);
        return "\n" +
                "Your email has been successfully confirmed. Now you can log into your account";
    }
    public boolean codeIsActive(String email, String code) {
        if (!verificRepository.findByEmail(email).getCode().equals(code)){
            return false;
        }
        return true;
    }

    public boolean isUserVerified(String email) {
        User user = verificRepository.findByEmail(email);
        if (user.isActive()){
            return true;
        }
        return false;
    }
}
