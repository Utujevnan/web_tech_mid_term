package com.smartstudy.controller;

import com.smartstudy.model.User;
import com.smartstudy.model.UserProfile;
import com.smartstudy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(
            @RequestBody User user,
            @RequestParam Long villageId) {
        UserProfile userProfile = UserProfile.builder().build();
        User savedUser = userService.createUser(user, userProfile, villageId);
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/{userId}/profile")
    public ResponseEntity<UserProfile> updateUserProfile(
            @PathVariable Long userId,
            @RequestBody UserProfile userProfile) {
        return ResponseEntity.ok(userService.updateUserProfile(userId, userProfile));
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/sorted")
    public ResponseEntity<List<User>> getAllUsersSorted(
            @RequestParam(defaultValue = "username") String sortBy) {
        return ResponseEntity.ok(userService.getAllUsersSorted(sortBy));
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<User>> getAllUsersPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(userService.getAllUsersPaginated(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping("/exists/email/{email}")
    public ResponseEntity<Boolean> emailExists(@PathVariable String email) {
        return ResponseEntity.ok(userService.emailExists(email));
    }

    @GetMapping("/exists/username/{username}")
    public ResponseEntity<Boolean> usernameExists(@PathVariable String username) {
        return ResponseEntity.ok(userService.usernameExists(username));
    }

    @GetMapping("/province/{provinceId}")
    public ResponseEntity<List<User>> getUsersByProvince(@PathVariable Long provinceId) {
        return ResponseEntity.ok(userService.getUsersByProvince(provinceId));
    }
}