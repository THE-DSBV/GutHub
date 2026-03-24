package com.guthub.backend.controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guthub.backend.model.User;
import com.guthub.backend.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> userData) {
        String username = userData.get("username");
        String rawPassword = userData.get("password");


        if (userRepository.findByUsername(username).isPresent()) {
            System.err.println("Error: Username '" + username + "' is already taken.");
            return ResponseEntity.badRequest().body(Map.of("error", "Username already taken"));
        }

        User newUser = new User(username, rawPassword);
        userRepository.save(newUser);

        return ResponseEntity.ok(Map.of("message", "User registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");


        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));


        if (!password.equals(user.getPassword())) {
            return ResponseEntity.status(401).body(Map.of(
                "status", "error",
                "message", "Invalid username or password"
            ));
        }


        String sessionToken = UUID.randomUUID().toString();
        return ResponseEntity.ok(Map.of(
            "username", username,
            "status", "success",
            "token", sessionToken,
            "isAdmin", user.getIsAdmin()
        ));
    }
}