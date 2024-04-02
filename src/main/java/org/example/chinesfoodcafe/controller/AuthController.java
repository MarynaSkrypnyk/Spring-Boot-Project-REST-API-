package org.example.chinesfoodcafe.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.chinesfoodcafe.entity.User;
import org.example.chinesfoodcafe.model.LoginRequest;
import org.example.chinesfoodcafe.model.LoginResponse;
import org.example.chinesfoodcafe.model.RegistrationRequest;
import org.example.chinesfoodcafe.service.AuthService;
import org.example.chinesfoodcafe.service.UserService;
import org.example.chinesfoodcafe.utils.Role;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request) {
        return authService.attemptLogin(request.getEmail(), request.getPassword());
    }

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public String registration(@RequestBody @Valid RegistrationRequest registrationRequest) {
        String password = registrationRequest.getPassword();
        String email = registrationRequest.getEmail();
        Role role = registrationRequest.getRole();
        String encoderPassword = BCryptPasswordEncoder(password);
        User user = new User(email, encoderPassword, role);

        log.info("registration : {}", registrationRequest);
        userService.register(user);
        return "User registration successful. An email has been sent to your email to confirm your identity";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return errors;
    }

    public static String BCryptPasswordEncoder(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String encoredPassword = encoder.encode(password);
        return encoredPassword;
    }
}



