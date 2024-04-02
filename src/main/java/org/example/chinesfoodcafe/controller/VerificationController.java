package org.example.chinesfoodcafe.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.chinesfoodcafe.service.VerificationService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verify-account")
@RequiredArgsConstructor
@Slf4j
public class VerificationController {

    private final VerificationService verificationService;

    @PutMapping()
    public String verification(@RequestParam String email,
                               @RequestParam String code) {

        if (!verificationService.codeIsActive(email, code)) {
            return "Activation code is outdated";
        }
        verificationService.verifyAccount(email, code);
        return "Your email has been successfully confirmed. Now you can log into your account";
    }
}
