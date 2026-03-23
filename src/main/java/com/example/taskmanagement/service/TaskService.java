package com.example.taskmanagement.service;

import com.example.taskmanagement.dto.TaskRequest;
import com.example.taskmanagement.entity.Task;
import com.example.taskmanagement.entity.TaskPriority;
import com.example.taskmanagement.entity.TaskStatus;
import com.example.taskmanagement.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.taskmanagement.repository.TaskRepository;
import com.example.taskmanagement.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    public List<Task> getAll(Long userId, TaskStatus status, TaskPriority priority) {
        if (userId != null && status != null && priority != null){
            return taskRepository.findByUserIdAndStatusAndPriority(userId, status, priority);
        }
        if (userId != null && status != null){
            return taskRepository.findByUserIdAndStatus(userId, status);
        }
        if (userId != null && priority != null){
            return taskRepository.findByUserIdAndPriority(userId, priority);
        }
        if (status != null && priority != null){
            return taskRepository.findByStatusAndPriority(status, priority);
        }
        if (userId != null){
        return taskRepository.findByUserId(userId);
        }
        if (status != null){
            return taskRepository.findByStatus(status);
        }
        if (priority != null){
            return taskRepository.findByPriority(priority);
        }
        return taskRepository.findAll();
    }
    public Task getById(Long id){
        return taskRepository.findById(id)
                .orElseThrow(()
                -> new RuntimeException("Task not found with id: " + id));
    }
    public Task create(TaskRequest request){
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(()->new RuntimeException("User not found with id: " + request.getUserId()));
        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(request.getStatus())
                .priority(request.getPriority())
                .dueDate(request.getDueDate())
                .user(user)
                .build();
        return taskRepository.save(task);
    }

    public Task update(Long id, TaskRequest request) {
        Task task = getById(id);
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id = " + request.getUserId()));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());
        task.setDueDate(request.getDueDate());
        task.setUser(user);

        return taskRepository.save(task);
    }
    public void delete(Long id){
        Task task = getById(id);
        taskRepository.delete(task);
    }

}
