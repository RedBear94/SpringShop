package com.spring.market.controllers;

import com.spring.market.dto.SystemUserDto;
import com.spring.market.exeptions.RegistrationError;
import com.spring.market.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerNewUser(@RequestBody @Validated SystemUserDto systemUser, BindingResult bindingResult) {
        if (userService.findByUsername(systemUser.getUsername()).isPresent()) {
            return new ResponseEntity<>(new RegistrationError("Username " + systemUser.getUsername() + " is busy"), HttpStatus.BAD_REQUEST);
        }
        if (!systemUser.getPassword().equals(systemUser.getConfirmationPassword())) {
            return new ResponseEntity<>(new RegistrationError("Password and confirmed password isn't equal"), HttpStatus.BAD_REQUEST);
        }
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(new RegistrationError(bindingResult.getAllErrors()), HttpStatus.BAD_REQUEST);
        }
        userService.saveUserFromDtoAsEntity(systemUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
