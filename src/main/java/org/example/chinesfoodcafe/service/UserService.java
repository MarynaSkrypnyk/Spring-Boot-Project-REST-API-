package org.example.chinesfoodcafe.service;


import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.chinesfoodcafe.entity.User;
import org.example.chinesfoodcafe.exeption.MailException;
import org.example.chinesfoodcafe.exeption.ThereIsNoSuchUserException;
import org.example.chinesfoodcafe.exeption.UserWithEmailExistException;
import org.example.chinesfoodcafe.repository.UserRepository;

import org.example.chinesfoodcafe.utils.ActivatedCode;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Cacheable(value = "user")
public class UserService {
    private final ActivatedCode activatedCode;
    private final EmailService emailService;
    private final UserRepository userRepository;

    @Cacheable(value = "user")
    public User register(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserWithEmailExistException();
        } String code = activatedCode.generateCode();
            try {
                emailService.sendOtpEmail(user.getEmail(), code);
            } catch (MessagingException e) {
                throw new MailException();
            }
            User userRegister = new User();
            userRegister.setEmail(user.getEmail());
            userRegister.setPassword(user.getPassword());
            userRegister.setCode(code);
            userRegister.setRole(user.getRole());
            userRepository.save(userRegister);
        log.info("register user: {}",user);
        return user;
    }

    public User getUserById(Long id) {
        User userById = userRepository.findUserById(id);
        if (userById == null) {
            throw new ThereIsNoSuchUserException();
        }
        log.info("get user by id : {}",id);
        return userById;
    }

    @CacheEvict(value = "user")
    public String deleteUserById(Long id) {
        userRepository.deleteById(id);
        log.info("delete user : {}",id);
        return "User delete from UserBase";
    }

    @Cacheable(value = "user")
    public Optional<User> findByEmail(String email) {
        log.info("findByEmail user : {}",email);
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsers(PageRequest pageRequest) {
        Page<User> page = userRepository.getAllUsers(pageRequest);
        log.info("getAllUsers pageRequest : {}",pageRequest);
        return page.getContent();
    }

    @CachePut (value = "user")
    public User updateUser(User user, Long id) {
        User updateUser = userRepository.findById(id).orElseThrow();
        updateUser.setEmail(user.getEmail());
        updateUser.setPassword(user.getPassword());
        updateUser.setRole(user.getRole());
        log.info("updateUser : {}",id);
        return userRepository.save(updateUser);
    }

}
