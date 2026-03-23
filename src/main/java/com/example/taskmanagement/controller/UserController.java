package com.example.taskmanagement.controller;

import com.example.taskmanagement.dto.UserRequest;
import com.example.taskmanagement.entity.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.example.taskmanagement.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;
    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }
    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userService.getById(id);
    }
    @PostMapping
    public User create(@RequestBody @Valid UserRequest request) {
        return userService.create(request);
    }
    @PutMapping("/{id}")
    public User create(@PathVariable Long id, @RequestBody @Valid UserRequest request) {
        return userService.update(id, request);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        userService.delete(id);
        return "Deleted";
    }


}
