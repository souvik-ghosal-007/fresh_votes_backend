package com.souvik.fresh_votes.rest;

import com.souvik.fresh_votes.domain.User;
import com.souvik.fresh_votes.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public ResponseEntity<String> handleLogin(@RequestParam String username, @RequestParam String password) {
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/register")
    public ResponseEntity<User> handleRegister(@RequestBody User user) {

        logger.info(user.toString());

        userService.save(user);

        logger.info("Saved in database");
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username) {
        User user = userService.findByUsername(username);

        return ResponseEntity.ok(user);
    }
}
