package com.example.taskmanagement.service;

import com.example.taskmanagement.dto.UserRequest;
import com.example.taskmanagement.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.taskmanagement.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public List<User> getAll() {
        return userRepository.findAll();
    }
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new RuntimeException("User not found with id: " + id));
    }
    public User create(UserRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .role(request.getRole())
                .build();
        return userRepository.save(user);
    }
    public User update(Long id, UserRequest request) {
        User user = getById(id);
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        return userRepository.save(user);
    }
    public void delete(Long id) {
        User user = getById(id);
        userRepository.delete(user);
    }

}
