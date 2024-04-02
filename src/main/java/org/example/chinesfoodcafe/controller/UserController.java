package org.example.chinesfoodcafe.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.example.chinesfoodcafe.entity.User;
import org.example.chinesfoodcafe.service.UserService;
import org.example.chinesfoodcafe.utils.Role;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        log.info("getUserById: {}", id);
        User userById = userService.getUserById(id);
        return new ResponseEntity<>(userById, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteUserById(@PathVariable Long id) {
        log.info("delete user: {}", id);
        userService.deleteUserById(id);
        return "User delete from base";
    }

    @GetMapping("/list")
    public List<User> getAllUsers(@RequestParam(required = false, defaultValue = "0") int page,
                                  @RequestParam(required = false, defaultValue = "5") int size
    ) {
        return userService.getAllUsers(PageRequest.of(page, size));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user, @PathVariable Long id) {
        String password = user.getPassword();
        String email = user.getEmail();
        Role role = user.getRole();
        String encoderPassword = BCryptPasswordEncoder(password);
        User userU = new User(encoderPassword, email, role);

        log.info("update user: {}", id);
        User userUpdate = userService.updateUser(userU, id);
        return new ResponseEntity<>(userUpdate, HttpStatus.CREATED);
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
