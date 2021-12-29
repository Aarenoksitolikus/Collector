package me.akvelon.collector.controllers;

import me.akvelon.collector.dto.ExceptionDto;
import me.akvelon.collector.exceptions.UserNotFoundException;
import me.akvelon.collector.models.Page;
import me.akvelon.collector.models.User;
import me.akvelon.collector.services.interfaces.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(usersService.getAll());
    }

    @GetMapping
    public ResponseEntity<Page<User>> getPageOfUsers(@RequestParam int limit, @RequestParam int offset) {
        return ResponseEntity.ok(usersService.getPage(limit, offset));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(usersService.getById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID = " + id + " not found")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        usersService.deleteUserById(id);
        return ResponseEntity.ok("User with ID = " + id + " was successfully deleted");
    }

    @PatchMapping("/{id}/{amount}")
    public ResponseEntity<String> changeUserBalance(@PathVariable Long id, @PathVariable String amount) {
        usersService.changeBalance(id, amount);
        return ResponseEntity.ok("The account of the user with ID = " + id +
                " was successfully changed to " + amount);
    }
}
