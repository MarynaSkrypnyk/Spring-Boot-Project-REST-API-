package org.example.chinesfoodcafe.exeption;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ThereIsNoSuchUserException.class)
    protected ResponseEntity<AwesomeException> handleThereIsNoSuchUserException() {
        return new ResponseEntity<>(new AwesomeException("There is no user with this id"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserWithEmailExistException.class)
    protected ResponseEntity<AwesomeException> handleUserWithSimpleEmail() {
        return new ResponseEntity<>(new AwesomeException("User with this email is already exist"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DishIsExistException.class)
    protected ResponseEntity<AwesomeException> handleDishIsExist() {
        return new ResponseEntity<>(new AwesomeException("Dish with this name is already exist"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoDishException.class)
    protected ResponseEntity<AwesomeException> handleDishNotExist() {
        return new ResponseEntity<>(new AwesomeException("Dish with this name don't already exist"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(VerificationException.class)
    protected ResponseEntity<AwesomeException> handleVerification() {
        return new ResponseEntity<>(new AwesomeException("User with this email don't verific"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MailException.class)
    protected ResponseEntity<AwesomeException> handleEmailNotSend() {
        return new ResponseEntity<>(new AwesomeException("\n" +
                "The verification letter was not sent. Please try to register late"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(OrderException.class)
    protected ResponseEntity<AwesomeException> handleOrderExeption() {
        return new ResponseEntity<>(new AwesomeException("\n" +
                "You can't make order. Because this email don't verificate!"), HttpStatus.CONFLICT);
    }

    @Data
    @AllArgsConstructor
    private static class AwesomeException {
        private String message;
    }
}
