package me.akvelon.collector.controllers;

import me.akvelon.collector.dto.UserForm;
import me.akvelon.collector.services.interfaces.SignUpService;
import me.akvelon.collector.services.interfaces.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class SignUpController {
    @Autowired
    private UsersService usersService;

    @Autowired
    private SignUpService signUpService;

    @PostMapping
    public ResponseEntity<UserForm> signUp(@RequestBody UserForm userForm) {
        return ResponseEntity.ok(signUpService.signUp(userForm));
    }
}
