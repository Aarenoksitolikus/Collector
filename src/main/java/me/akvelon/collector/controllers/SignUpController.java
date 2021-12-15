package me.akvelon.collector.controllers;

import me.akvelon.collector.dto.UserForm;
import me.akvelon.collector.models.User;
import me.akvelon.collector.services.interfaces.SignUpService;
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
    private SignUpService signUpService;

    @PostMapping
    public ResponseEntity<User> signUp(@RequestBody UserForm userForm) {
        return ResponseEntity.ok(signUpService.signUp(userForm));
    }
}
